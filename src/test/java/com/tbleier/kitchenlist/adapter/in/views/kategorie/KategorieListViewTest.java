package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KategorieListViewTest {
    @Mock
    private CommandService<SaveKategorieCommand> addKategorieCommandService;

    @Mock
    private CommandService<DeleteKategorieCommand> deleteKategorieCommandService;

    @Mock
    private QueryService<ListAllKategorienQuery, List<KategorieDTO>> listAllKategorienQueryService;




    private KategorieListView testee;
    private KategorieDTO firstKategorie;
    private KategorieDTO secondKategorie;

    @BeforeEach
    public void setUp() {

        firstKategorie = new KategorieDTO(5, "Gemüse");
        secondKategorie = new KategorieDTO(1, "Fleisch");

        givenKategorien(List.of(firstKategorie, secondKategorie));

        var mapper = KategorieModelMapper.INSTANCE;

        var formFactory = new KategorieFormFactory(
                addKategorieCommandService,
                mapper,
                deleteKategorieCommandService);

        testee = new KategorieListView(formFactory,
                listAllKategorienQueryService,
                deleteKategorieCommandService,
                mapper);
    }

    @Test
    public void should_show_all_kategorien_in_grid() {
        //Act
        var actual = testee.grid.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertThat(actual, hasSize(2));
        assertThat(actual, hasItems(firstKategorie, secondKategorie));
    }

    @Test
    public void should_not_be_in_editing_mode_initially() {

        //Assert
        assertThatEditingIs(false);
    }

    @Test
    public void should_leave_editing_mode_when_unselecting_an_item() {
        //Arrange
        testee.grid.select(new KategorieDTO());

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
    public void should_update_kategorie_list_when_kategorie_was_saved() {
        //Arrange
        firstKategorie.setName("someNewName");

        //Act
        testee.updateKategorie(firstKategorie);

        //Assert
        assertThatKategorienContain(firstKategorie);
    }

    @Test
    public void should_open_kategorie_in_editor() {
        //Arrange
        var kategorieModel = new KategorieDTO(1, "test");

        //Act
        testee.grid.select(kategorieModel);

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
        //Act
        testee.removeKategorie(firstKategorie);

        //Assert

        assertThatKategorienDoNotContain(firstKategorie);
    }



    private void givenKategorien(List<KategorieDTO> kategorien) {
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

    private void assertThatKategorienContain(KategorieDTO kategorie) {
        assertEquals(true, testee.getKategorieModels()
                .stream()
                .anyMatch(k -> k.getId() == kategorie.getId()));
    }

    private void assertThatKategorienDoNotContain(KategorieDTO kategorie) {
        assertEquals(false, testee.getKategorieModels()
                .stream()
                .anyMatch(k -> k.getId() == kategorie.getId()));
    }
}