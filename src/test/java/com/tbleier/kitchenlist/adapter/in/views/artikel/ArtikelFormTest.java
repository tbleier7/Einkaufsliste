package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtikelFormTest {

    @Mock
    private CommandService<SaveArtikelCommand> addRezeptCommandCommandService;
    @Captor
    ArgumentCaptor<SaveArtikelCommand> addZutatCommandCaptor;

    private List<String> kategorieNames;

    private ArtikelForm testee;

    @BeforeEach
    public void setUp() {
        Kategorie _gemuese = new Kategorie("Gemüse");
        kategorieNames = List.of("Obst", "Gemüse", "Fleisch");
        testee = new ArtikelForm(new ArtikelModel(),
                kategorieNames,
                addRezeptCommandCommandService);
    }

    @Test
    public void should_show_all_kategorien_in_comboBox() {
        //Arrange
    
        //Act
        List<String> actual = testee.kategorie.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertEquals(kategorieNames, actual);
    }
    
    @Test
    public void should_add_a_new_artikel_on_save_click() {
        //Arrange
        var expectedArtikel = new Artikel("Zwiebeln", Einheit.Stueck, new Kategorie(kategorieNames.get(1)));

        testee.name.setValue(expectedArtikel.getName());
        testee.einheit.setValue(expectedArtikel.getEinheit());
        testee.kategorie.setValue(kategorieNames.get(1));

        //Act
        testee.save.click();

        //Assert
        verify(addRezeptCommandCommandService).execute(addZutatCommandCaptor.capture());
        var addZutatCommand = addZutatCommandCaptor.getValue();
        assertEquals(expectedArtikel, addZutatCommand.getZutat());
    }
}