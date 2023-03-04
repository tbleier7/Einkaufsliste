package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KategorieListViewTest {
    @Mock
    private CommandService<SaveKategorieCommand> addKategorieCommandService;

    @Mock
    private CommandService<DeleteKategorieCommand> deleteKategorieCommandService;

    @Mock
    private QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService;

    @Captor
    ArgumentCaptor<DeleteKategorieCommand> deleteKategorieCommandCaptor;


    private KategorieListView testee;

    @BeforeEach
    public void setUp() {

        givenKategorien(List.of(new Kategorie("Gemüse"), new Kategorie("Fleisch")));

        var mapper = KategorieModelMapper.INSTANCE;

        var formFactory = new KategorieFormFactory(
                addKategorieCommandService,
                deleteKategorieCommandService,
                mapper);

        testee = new KategorieListView(formFactory,
                listAllKategorienQueryService,
                deleteKategorieCommandService,
                mapper);
    }

    @Test
    public void Should_show_all_kategorien() {
        //Arrange
        KategorieModel expectedKategorieModel1 = new KategorieModel();
        expectedKategorieModel1.setName("Gemüse");

        KategorieModel expectedKategorieModel2 = new KategorieModel();
        expectedKategorieModel2.setName("Fleisch");

        //Act
        var actual = testee.grid.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertThat(actual, hasSize(2));
        assertThat(actual, hasItems(expectedKategorieModel1, expectedKategorieModel2));
    }

    @Test
    public void should_not_be_in_editing_mode_initially() {

        //Assert
        assertThatEditingIs(false);
    }

    @Test
    public void should_leave_editing_mode_when_unselecting_an_item() {
        //Arrange
        testee.grid.select(new KategorieModel());

        //Act
        testee.grid.deselectAll();

        //Assert
        assertThatEditingIs(false);
    }

    @Test
    public void should_open_form_when_new_kategorie_should_be_added() {
        //Act
        testee.addKategorieButton.click();

        //Assert
        assertThatEditingIs(true);
    }

    @Test
    public void should_close_form_when_requested() {
        //Arrange
        testee.addKategorieButton.click();

        //Act
        testee.kategorieForm.cancel.click();

        //Assert
        assertThatEditingIs(false);
    }

    @Test
    public void should_delete_kategorie() {
        givenDeleteIs(true);
        var kategorieModel = new KategorieModel();
        kategorieModel.setName("Gemüse");

        //Act
        testee.deleteKategorie(kategorieModel);

        //Assert
        verify(deleteKategorieCommandService).execute(deleteKategorieCommandCaptor.capture());
        var deleteKategorieCommand = deleteKategorieCommandCaptor.getValue();
        assertEquals(new Kategorie("Gemüse"), deleteKategorieCommand.getKategorie());
    }

    private void givenDeleteIs(boolean successful) {
        when(deleteKategorieCommandService.execute(any())).thenReturn(new CommandResult(successful));
    }


    private void givenKategorien(List<Kategorie> kategorien) {
        when(listAllKategorienQueryService.execute(any())).thenReturn(kategorien);
    }

    private void assertThatEditingIs(boolean editing) {
        var classnames = testee.getClassNames();
        var editingMode = "editing";

        if (editing)
            assertThat(classnames, hasItem(editingMode));
        else
            assertThat(classnames, not(hasItem(editingMode)));
    }
}