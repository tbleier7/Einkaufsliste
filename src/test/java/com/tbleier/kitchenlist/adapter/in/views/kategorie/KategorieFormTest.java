package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KategorieFormTest {

    @Mock
    private CommandService<SaveKategorieCommand> addKategorieCommandService;

    @Captor
    ArgumentCaptor<SaveKategorieCommand> addKategorieCommandCaptor;

    private KategorieForm testee;

    @BeforeEach
    public void setUp() {
        testee = new KategorieForm(new Kategorie("Irgendwas"), addKategorieCommandService );
    }
    
    @Test
    public void Should_save_kategorie_on_save_click() {
        //Arrange
        var expectedKategorie = new Kategorie("Gemuese");
        testee.name.setValue(expectedKategorie.getName());
    
        //Act
        testee.save.click();
    
        //Assert
        verify(addKategorieCommandService).execute(addKategorieCommandCaptor.capture());
        var addZutatCommand = addKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorie, addZutatCommand.getKategorie());
    }

}