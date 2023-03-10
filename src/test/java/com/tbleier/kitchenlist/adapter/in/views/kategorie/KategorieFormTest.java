package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KategorieFormTest {

    @Mock
    private CommandService<SaveKategorieCommand> saveKategorieCommandService;

    private KategorieModelMapper mapper;

    @Captor
    ArgumentCaptor<SaveKategorieCommand> saveKategorieCommandCaptor;


    private KategorieForm testee;
    private AtomicBoolean saveEventWasFired;
    private AtomicBoolean deleteEventWasFired;

    private Kategorie expectedKategorie;

    @BeforeEach
    public void setUp() {
        mapper = KategorieModelMapper.INSTANCE;
        testee = new KategorieForm(new KategorieModel(), saveKategorieCommandService, mapper);

        saveEventWasFired = new AtomicBoolean(false);
        testee.addListener(SaveKategorieEvent.class, e -> {
            saveEventWasFired.set(true);
        });

        deleteEventWasFired = new AtomicBoolean(false);
        testee.addListener(DeleteKategorieEvent.class, e -> {
            deleteEventWasFired.set(true);
        });
    }

    @Test
    public void should_save_kategorie_on_save_click() {
        //Arrange
        givenSaveIs(true);
        var expectedKategorieName = "Gemuese";
        testee.name.setValue(expectedKategorieName);

        //Act
        testee.save.click();

        //Assert
        verify(saveKategorieCommandService).execute(saveKategorieCommandCaptor.capture());
        var saveKategorieCommand = saveKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorieName, saveKategorieCommand.getName());
    }

    @Test
    public void should_fire_event_when_kategorie_is_saved() {
        //Arrange
        givenSaveIs(true);
        givenNewKategorie("Gemüse");

        //Act
        testee.save.click();

        //Assert
        assertEquals(true, saveEventWasFired.get());
    }

    @Test
    public void should_not_send_event_when_save_fails() {
        //Arrange
        givenNewKategorie("Gemüse");

        //Act
        testee.save.click();

        //Assert
        assertEquals(false, saveEventWasFired.get());
    }
    
    @Test
    public void should_not_save_when_name_is_empty() {
        //Arrange
        givenNewKategorie("");
    
        //Act
        testee.save.click();
    
        //Assert
        verifyNoInteractions(saveKategorieCommandService);
        assertEquals(false, saveEventWasFired.get());
    }

    private void givenNewKategorie(String name) {
        expectedKategorie = new Kategorie(1, name);
        testee.name.setValue(expectedKategorie.getName());
    }

    private void givenSaveIs(boolean successful) {
        when(saveKategorieCommandService.execute(any())).thenReturn(new CommandResult(successful));
    }

}