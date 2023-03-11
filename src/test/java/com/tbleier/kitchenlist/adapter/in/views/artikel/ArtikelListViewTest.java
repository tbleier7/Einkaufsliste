package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtikelListViewTest {

    @Mock
    private CommandService<SaveArtikelCommand> saveArtikelCommandService;

    @Mock
    private QueryService<ListArtikelQuery, List<ArtikelDTO>> listAllArtikelQueryService;

    @Mock
    private QueryService<ListAllKategorienQuery, List<KategorieDTO>> listAllKategorienQueryService;

    private ArtikelListView testee;
    private List<ArtikelDTO> expectedArtikel;

    @BeforeEach
    public void setUp() {
        var mapper = ArtikelDTOMapper.INSTANCE;
        var factory = new ArtikelFormFactory(saveArtikelCommandService, listAllKategorienQueryService);

        givenTwoArtikel();
        givenAKategorie();
        testee = new ArtikelListView(factory, listAllArtikelQueryService, mapper);
    }

    private void givenAKategorie() {
        when(listAllKategorienQueryService.execute(any())).thenReturn(List.of(new KategorieDTO(0, "someKategorie")));
    }

    @Test
    public void should_hide_editing_form_when_initialized() {
        //Assert
        assertEquals(false,testee.artikelForm.isVisible());
    }
    
    @Test
    public void should_open_editing_form_when_adding_a_new_artikel() {
        //Act
        testee.addRezeptButton.click();

        //Assert
        assertEquals(true,testee.artikelForm.isVisible());
    }
    
    @Test
    public void should_show_all_artikel() {
        //Act
        var actual = testee.grid.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
    
        //Assert
        assertEquals(2, actual.size());
    }
    
    @Test
    public void should_add_artikel_when_new_artikel_was_saved() {
        //Arrange
        var artikelModel = new ArtikelDTO(1, "newcomer", Einheit.Stueck, "Gemüse");
        //Act
        testee.addArtikel(artikelModel);
    
        //Assert
        assertThat(testee.getArtikelDTOS(), hasItem(artikelModel));
    }

    @Test
    public void should_open_selected_artikel_in_editor() {
        //Arrange
        var artikelModel = new ArtikelDTO(1, "test", Einheit.Stueck, "Gemüse");

        //Act
        testee.grid.select(artikelModel);

        //Assert
        assertEquals(true,testee.artikelForm.isVisible());
        assertEquals(artikelModel.getName(), testee.artikelForm.name.getValue());
    }

    private void givenTwoArtikel() {
        expectedArtikel = List.of(
                new ArtikelDTO(1,"Zwiebeln", Einheit.Stueck, "Gemüse"),
                new ArtikelDTO(2, "Schinken", Einheit.Stueck, "Gemüse"));

        when(listAllArtikelQueryService.execute(any())).thenReturn(expectedArtikel);
    }
}