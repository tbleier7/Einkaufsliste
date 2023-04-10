package com.tbleier.kitchenlist.adapter.in.views.checkliste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.DecrementZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.IncrementZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.MoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListZutatenQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class EinkaufslisteListViewTest {

    @Mock
    private QueryService<ListZutatenQuery, List<ZutatDTO>> einkaufsListeQueryService;

    @Mock
    private CommandService<RemoveZutatCommand> removeZutatCommandService;

    @Mock
    private CommandService<MoveZutatCommand> moveZutatCommandService;

    @Mock
    private CommandService<IncrementZutatCommand> incrementZutatCommandService;

    @Mock
    private CommandService<DecrementZutatCommand> decrementZutatCommandService;

    @Mock
    private AddArtikelDialog addArtikelDialog;

    @Mock
    private AddArtikelDialogFactory addArtikelDialogFactory;

    private EinkaufslisteListView testee;
    private List<ZutatDTO> zutaten;

    @BeforeEach
    public void setUp() {
        zutaten = List.of(new ZutatDTO(15L, 1L, "Eier", 2),
                new ZutatDTO(23L, 12L, "Wurst", 3));
        givenTwoEntriesForEinkaufsliste();
        testee = new EinkaufslisteListView(einkaufsListeQueryService,
                addArtikelDialogFactory,
                removeZutatCommandService,
                moveZutatCommandService,
                incrementZutatCommandService,
                decrementZutatCommandService);
    }

    @Test
    public void should_show_all_einkaufslistenpositionen() {
        //Assert
        assertEquals(2, testee.getZutatDTOs().size());
    }
    
    @Test
    public void should_open_a_dialog_for_adding_an_artikel() {
        //Arrange
        when(addArtikelDialogFactory.CreateDialog()).thenReturn(addArtikelDialog);
    
        //Act
        testee.addArtikelButton.click();
    
        //Assert
        verify(addArtikelDialog).open();
    }

    @Test
    public void should_add_new_einkaufslistenposition() {
        //Arrange
        var listenposition = new ZutatDTO(0L, 515L, "irgendeinArtikel", 2);

        //Act
        testee.addEinkaufslistenposition(listenposition);

        //Assert
        assertThat(testee.getZutatDTOs(), hasItem(listenposition));
    }
    
    @Test
    public void should_remove_zutat() {
        //Arrange
        var checkedZutat = zutaten.get(0);
        givenRemoveZutatCommandIs(true);

        //Act
        testee.removeZutat(checkedZutat);
    
        //Assert
        verify(removeZutatCommandService).execute(argThat(command -> command.getZutatId() == checkedZutat.getId()));
        assertThat(testee.getZutatDTOs(), not(hasItem(checkedZutat)));
    }

    @Test
    public void should_not_remove_zutat_when_command_failed() {
        //Arrange
        var checkedZutat = zutaten.get(0);
        givenRemoveZutatCommandIs(false);

        //Act
        testee.removeZutat(checkedZutat);

        //Assert
        assertThat(testee.getZutatDTOs(), hasItem(checkedZutat));
    }

    private void givenRemoveZutatCommandIs(boolean successful) {
        when(removeZutatCommandService.execute(any())).thenReturn(new CommandResult(successful, 1L));
    }

    private void givenTwoEntriesForEinkaufsliste() {
        when(einkaufsListeQueryService.execute(any())).thenReturn(zutaten);
    }
}