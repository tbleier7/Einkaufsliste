package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.AddToEinkaufslisteService;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddToEinkaufslisteServiceTest {

    @Mock
    ArtikelRepository artikelRepository;

    @Mock
    EinkaufslisteRepository einkaufslisteRepository;

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
        var expectedZutatId = 5L;
        givenArtikelFromCommand();
        givenEmptyEinkaufsliste();
        givenZutatWasSavedWithId(expectedZutatId);

        //Act
        var actual = testee.execute(command);

        //Assert
        AssertThatZutatWasSuccessfullySaved(actual,expectedZutatId);
    }

    private void givenZutatWasSavedWithId(long zutatId) {
        when(einkaufslisteRepository.saveZutat(any())).thenReturn(zutatId);
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

    private void givenEmptyEinkaufsliste() {
        when(einkaufslisteRepository.getEinkaufsliste()).thenReturn(Einkaufsliste.CreateWithZutaten(Collections.emptyList()));
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

    private void AssertThatZutatWasSuccessfullySaved(CommandResult actual, long expectedZutatId) {
        assertNotEquals(0, actual.getId());
        assertTrue(actual.isSuccessful());
        assertEquals(expectedZutatId, actual.getId());
    }

    private void AssertThatCommandFailed(CommandResult actual) {
        verifyNoInteractions(einkaufslisteRepository);
        assertFalse(actual.isSuccessful());
    }
}