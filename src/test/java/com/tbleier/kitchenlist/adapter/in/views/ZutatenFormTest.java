package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddZutatCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ZutatenFormTest {

    @Mock
    private CommandService<AddZutatCommand> addRezeptCommandCommandService;
    @Captor
    ArgumentCaptor<AddZutatCommand> addZutatCommandCaptor;

    private List<Kategorie> kategorien;

    private ZutatenForm testee;

    @BeforeEach
    public void setUp() {
        Kategorie _gemuese = new Kategorie("Gemüse");
        kategorien = List.of(new Kategorie("Obst"), _gemuese);
        testee = new ZutatenForm(new Zutat("", Einheit.Gramm, null),
                kategorien,
                addRezeptCommandCommandService);
    }

    @Test
    public void should_save_a_new_zutat() {
        //Arrange
        var expectedZutat = new Zutat("Zwiebeln", Einheit.Stueck, kategorien.get(1));

        testee.name.setValue(expectedZutat.getName());
        testee.einheit.setValue(expectedZutat.getEinheit());
        testee.kategorie.setValue(expectedZutat.getKategorie());

        //Act
        testee.save.click();

        //Assert
        verify(addRezeptCommandCommandService).execute(addZutatCommandCaptor.capture());
        var addZutatCommand = addZutatCommandCaptor.getValue();
        assertEquals(expectedZutat, addZutatCommand.getZutat()); // Da stimmt was nicht!!!
    }
}