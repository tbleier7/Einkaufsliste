package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
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

        testee = new AddArtikelDialog(listArtikelQueryService, addToEinkaufslisteCommandService);
        testee.addListener(SaveListeneintragEvent.class, e -> {
            saveEventWasFired.set(true);
        });
    }

    @Test
    public void should_not_query_artikel_until_instantiating() {
        //Act
        List<ArtikelDTO> artikelDTOs = testee.getArtikelDTOs();
        assertEquals(0, artikelDTOs.size());
    }
    
    @Test
    public void should_show_all_artikel_after_initializing() {
        //Arrange
        givenTwoArtikel();

        //Act
        testee.initialize();

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
        givenTwoArtikel();
        var selectedArtikel = artikelDTOs.get(0);
        givenArtikelWasSelected(selectedArtikel);


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
        givenTwoArtikel();
        var selectedArtikel = artikelDTOs.get(0);
        givenArtikelWasSelected(selectedArtikel);


        //Act
        testee.addButton.click();

        //Assert
        assertEquals(true, saveEventWasFired.get());
    }

    private void givenArtikelWasSelected(ArtikelDTO artikelDTO) {
        testee.initialize();
        testee.artikelDTOComboBox.setValue(artikelDTO);
    }

    private void givenTwoArtikel() {
        artikelDTOs = List.of(new ArtikelDTO(1, "Wurst", Einheit.Stueck, "Fleisch"),
                new ArtikelDTO(2, "Brei", Einheit.Gramm, "Einerlei"));
        when(listArtikelQueryService.execute(any())).thenReturn(artikelDTOs);
    }
}