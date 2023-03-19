package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListKategorienQuery;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtikelFormTest {

    @Mock
    private CommandService<SaveArtikelCommand> saveArtikelCommandService;
    @Mock
    private CommandService<DeleteArtikelCommand> deleteArtikelCommandService;
    @Mock
    private QueryService<ListKategorienQuery, List<KategorieDTO>> listKategorienQueryService;
    @Captor
    ArgumentCaptor<SaveArtikelCommand> saveCommandCaptor;

    @Captor
    ArgumentCaptor<DeleteArtikelCommand> deleteCommandCaptor;

    private List<String> kategorieNames;

    private ArtikelForm testee;
    private AtomicBoolean saveEventWasFired;
    private AtomicBoolean cancelEventWasFired;
    private AtomicBoolean deleteEventWasFired;

    @BeforeEach
    public void setUp() {
        givenTwoKategorien();
        
        testee =  new ArtikelForm(new ArtikelDTO(),
                saveArtikelCommandService, listKategorienQueryService, deleteArtikelCommandService);

        saveEventWasFired = new AtomicBoolean(false);
        testee.addListener(SaveArtikelEvent.class, e -> {
            saveEventWasFired.set(true);
        });

        cancelEventWasFired = new AtomicBoolean(false);
        testee.addListener(CloseEvent.class, e -> {
            cancelEventWasFired.set(true);
        });

        deleteEventWasFired = new AtomicBoolean(false);
        testee.addListener(DeleteArtikelEvent.class, e -> {
            deleteEventWasFired.set(true);
        });

    }
    
    @Test
    public void should_show_all_kategorien_in_comboBox() {
        //Act
        List<String> actual = testee.kategorie.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertEquals(kategorieNames, actual);
    }

    @Test
    public void should_add_a_new_artikel_on_save_click() {
        //Arrange
        givenSaveWasSuccessful();
        var expectedArtikel = new Artikel(0, "Zwiebeln", Einheit.Stueck, new Kategorie(1, kategorieNames.get(1)));

        testee.name.setValue(expectedArtikel.getName());
        testee.einheit.setValue(expectedArtikel.getEinheit());
        testee.kategorie.setValue(kategorieNames.get(1));

        //Act
        testee.save.click();

        //Assert
        verify(saveArtikelCommandService).execute(saveCommandCaptor.capture());
        var saveArtikelCommand = saveCommandCaptor.getValue();
        assertEquals(expectedArtikel.getId(), saveArtikelCommand.getId());
        assertEquals(expectedArtikel.getName(), saveArtikelCommand.getName());
        assertEquals(expectedArtikel.getEinheit(), saveArtikelCommand.getEinheit());
        assertEquals(expectedArtikel.getKategorie().getName(), saveArtikelCommand.getKategorie());

        assertEquals(true, saveEventWasFired.get());
    }

    private void givenTwoKategorien() {
        kategorieNames = List.of("Gemüse", "Fleisch");
        when(listKategorienQueryService.execute(any()))
                .thenReturn(List.of(new KategorieDTO(0, "Gemüse"), new KategorieDTO(1, "Fleisch")));
    }

    @Test
    public void should_send_request_for_closing_the_form() {
        //Arrange

        //Act
        testee.cancel.click();

        //Assert
        assertEquals(true, cancelEventWasFired.get());
    }
    
    @Test
    public void should_delete_an_artikel() {
        //Arrange
        var artikelToDelete = new ArtikelDTO(5, "ArtikelToDelete", Einheit.Stueck, "someKategorie");
        testee.setArtikelModel(artikelToDelete);

        //Act
        testee.delete.click();
    
        //Assert
        verify(deleteArtikelCommandService).execute(deleteCommandCaptor.capture());
        var deleteCommand = deleteCommandCaptor.getValue();
        assertEquals(artikelToDelete.getId(), deleteCommand.getId());
    }
    
    @Test
    public void should_fire_delete_event_when_artikel_was_deleted() {
        //Arrange
        var artikelToDelete = new ArtikelDTO(5, "ArtikelToDelete", Einheit.Stueck, "someKategorie");
        testee.setArtikelModel(artikelToDelete);

        //Act
        testee.delete.click();

        //Assert
        assertEquals(true, deleteEventWasFired.get());
    }

    private void givenSaveWasSuccessful() {
        when(saveArtikelCommandService.execute(any())).thenReturn(new CommandResult(true, 1L));
    }
}