package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KategorieFormTest {

    @Mock
    private CommandService<SaveKategorieCommand> saveKategorieCommandService;

    @Mock
    private CommandService<DeleteKategorieCommand> deleteKategorieCommandService;

    private KategorieModelMapper mapper;

    @Captor
    ArgumentCaptor<SaveKategorieCommand> saveKategorieCommandCaptor;

    @Captor
    ArgumentCaptor<DeleteKategorieCommand> deleteKategorieCommandCaptor;

    private KategorieForm testee;
    private AtomicBoolean saveEventWasFired;
    private AtomicBoolean deleteEventWasFired;

    private Kategorie expectedKategorie;

    @BeforeEach
    public void setUp() {
        mapper = KategorieModelMapper.INSTANCE;
        testee = new KategorieForm(new KategorieModel(), saveKategorieCommandService, deleteKategorieCommandService, mapper);

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
    public void Should_save_kategorie_on_save_click() {
        //Arrange
        givenSaveIs(true);
        var expectedKategorie = new Kategorie("Gemuese");
        testee.name.setValue(expectedKategorie.getName());

        //Act
        testee.save.click();

        //Assert
        verify(saveKategorieCommandService).execute(saveKategorieCommandCaptor.capture());
        var saveKategorieCommand = saveKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorie, saveKategorieCommand.getKategorie());
    }

    @Test
    public void Should_fire_event_when_kategorie_is_saved() {
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
        givenSaveIs(false);

        //Act
        testee.save.click();

        //Assert
        assertEquals(false, saveEventWasFired.get());
    }

    @Test
    public void should_delete_kategorie_on_delete_click() {
        givenDeleteIs(true);
        givenCurrentKategorie("Gemüse");

        //Act
        testee.delete.click();

        //Assert
        verify(deleteKategorieCommandService).execute(deleteKategorieCommandCaptor.capture());
        var deleteKategorieCommand = deleteKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorie, deleteKategorieCommand.getKategorie());
    }

    @Test
    public void should_fire_event_when_deleting_a_kategorie() {
        givenDeleteIs(true);
        givenCurrentKategorie("Gemüse");

        //Act
        testee.delete.click();

        //Assert
        assertEquals(true, deleteEventWasFired.get());
    }

    @Test
    public void should_not_fire_event_when_deleting_fails() {
        givenDeleteIs(false);

        //Act
        testee.delete.click();

        //Assert
        assertEquals(false, deleteEventWasFired.get());
    }

    private void givenNewKategorie(String name) {
        expectedKategorie = new Kategorie(name);
        testee.name.setValue(expectedKategorie.getName());
    }

    private void givenCurrentKategorie(String name) {
        var model = new KategorieModel();
        model.setName(name);
        expectedKategorie = new Kategorie(model.getName());
        testee.setKategorieModel(model);
    }

    private void givenSaveIs(boolean successful) {
        when(saveKategorieCommandService.execute(any())).thenReturn(new CommandResult(successful));
    }

    private void givenDeleteIs(boolean successful) {
        when(deleteKategorieCommandService.execute(any())).thenReturn(new CommandResult(successful));
    }
}