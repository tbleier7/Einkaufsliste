package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.checkliste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.QueryService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.queries.ListArtikelQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(MockitoExtension.class)
class AddArtikelDialogTest {

    @Mock
    private QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;

    @Mock
    private CommandService<AddToEinkaufsListeCommand> addToEinkaufslisteCommandService;
    private List<ArtikelDTO> artikelDTOs;

    private AtomicBoolean saveEventWasFired = new AtomicBoolean();

    private AddArtikelDialog testee;

    @BeforeEach
    public void setUp() {

        givenTwoArtikel();

        testee = new AddArtikelDialog(listArtikelQueryService, addToEinkaufslisteCommandService);
        testee.addListener(AddZutatEvent.class, e -> {
            saveEventWasFired.set(true);
        });
    }

    private void givenAddCommandIs(boolean successful) {
        when(addToEinkaufslisteCommandService.execute(any())).thenReturn(new CommandResult(successful, 1L));
    }

    @Test
    public void should_show_all_artikel() {

        //Assert
        List<ArtikelDTO> artikelDTOs = testee.getArtikelDTOs();
        assertEquals(2, artikelDTOs.size());
    }

    @Test
    public void should_close_on_cancel() {
        //Act
        testee.cancelButton.click();

        //Assert
        assertEquals(false, testee.isOpened());
    }

    @Test
    public void should_have_default_menge_1() {

        //Assert
        assertEquals(1, testee.mengenIntegerField.getValue());
    }

    @Test
    public void should_add_selected_artikel_to_einkaufsliste() {
        //Arrange
        var selectedArtikel = artikelDTOs.get(0);
        givenArtikelWasSelected(selectedArtikel);
        givenAddCommandIs(true);

        //Act
        testee.addButton.click();

        //Assert
        verify(addToEinkaufslisteCommandService)
                .execute(argThat(command -> command.getArtikelId() == selectedArtikel.getId()));
    }

    @Test
    public void should_not_be_able_to_add_when_no_artikel_is_selected() {
        //Act
        testee.artikelDTOComboBox.setValue(null);

        //Assert
        assertEquals(false, testee.addButton.isEnabled());
    }

    @Test
    public void should_not_be_able_to_add_when_menge_is_smaller_1() {
        //Act
        testee.mengenIntegerField.setValue(0);

        //Assert
        assertEquals(false, testee.addButton.isEnabled());
    }
    
    @Test
    public void should_send_event_when_adding_to_the_einkaufsliste() {
        //Arrange
        var selectedArtikel = artikelDTOs.get(0);
        givenArtikelWasSelected(selectedArtikel);
        givenAddCommandIs(true);

        //Act
        testee.addButton.click();

        //Assert
        assertEquals(true, saveEventWasFired.get());
    }

    @Test
    public void should_not_send_event_when_add_failed() {
        //Arrange
        var selectedArtikel = artikelDTOs.get(0);
        givenArtikelWasSelected(selectedArtikel);
        givenAddCommandIs(false);

        //Act
        testee.addButton.click();

        //Assert
        assertEquals(false, saveEventWasFired.get());
    }

    private void givenArtikelWasSelected(ArtikelDTO artikelDTO) {
        testee.artikelDTOComboBox.setValue(artikelDTO);
    }

    private void givenTwoArtikel() {
        artikelDTOs = List.of(new ArtikelDTO(1, "Wurst", Einheit.Stueck, "Fleisch"),
                new ArtikelDTO(2, "Brei", Einheit.Gramm, "Einerlei"));
        when(listArtikelQueryService.execute(any())).thenReturn(artikelDTOs);
    }
}