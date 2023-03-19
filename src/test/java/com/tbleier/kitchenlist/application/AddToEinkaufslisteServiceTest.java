package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Einkaufslistenposition;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddToEinkaufslisteServiceTest {

    @Mock
    ArtikelRepository artikelRepository;

    @Mock
    EinkaufslisteRepository einkaufslisteRepository;

    @Captor
    ArgumentCaptor<Einkaufslistenposition> repositorySaveCaptor;

    private AddToEinkaufsListeCommand command;
    private AddToEinkaufslisteService testee;

    @BeforeEach
    public void setUp() {
        command = new AddToEinkaufsListeCommand(1L, 1);
        testee = new AddToEinkaufslisteService(einkaufslisteRepository, artikelRepository);
    }

    @Test
    public void should_add_artikel_to_einkaufsliste() {
        //Arrange
        givenArtikelFromCommand();

        //Act
        var actual = testee.execute(command);

        //Assert
        AssertThatListenEintragWasSuccessfullySaved(actual);
    }

    @Test
    public void should_fail_when_artikel_cant_be_found() {
        //Arrange
        givenArtikelWasNotFound();

        //Act
        var actual = testee.execute(command);

        //Assert
        AssertThatCommandFailed(actual);
    }

    private void givenArtikelWasNotFound() {
        when(artikelRepository.findById(anyLong())).thenReturn(Optional.empty());
    }

    private void givenArtikelFromCommand() {

        when(artikelRepository.findById(command.getArtikelId()))
                .thenReturn(Optional.of(
                        new Artikel(command.getArtikelId(), "AddedArtikel", Einheit.Stueck,
                                new Kategorie(2L, "Gemüse")
                        )
                ));
    }

    private void AssertThatListenEintragWasSuccessfullySaved(CommandResult actual) {
        verify(einkaufslisteRepository).save(repositorySaveCaptor.capture());
        var addedListenEintrag = repositorySaveCaptor.getValue();

        assertEquals(command.getMenge(), addedListenEintrag.getMenge());
        assertEquals(command.getArtikelId(), addedListenEintrag.getArtikel().getId());

        assertTrue( actual.isSuccessful());
    }

    private void AssertThatCommandFailed(CommandResult actual) {
        verifyNoInteractions(einkaufslisteRepository);
        assertFalse(actual.isSuccessful());
    }
}