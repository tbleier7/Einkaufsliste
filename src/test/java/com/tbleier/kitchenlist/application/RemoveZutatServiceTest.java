package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveZutatServiceTest {

    @Mock
    private EinkaufslisteRepository einkaufslisteRepository;

    private RemoveZutatService testee;
    
    @BeforeEach
    public void setUp() {
        testee = new RemoveZutatService(einkaufslisteRepository);
    }
    
    @Test
    public void should_remove_zutat_from_repository() {
        //Arrange
        var einkaufsliste = Einkaufsliste.CreateEmpty();
        var zutat = einkaufsliste.addExistingZutat(15L, new Artikel(23L, "Wurst", Einheit.Stueck, new Kategorie(324L, "Fleischwaren")), 3);

        givenEinkaufsliste(einkaufsliste);

        //Act
        var actual = testee.execute(new RemoveZutatCommand(zutat.getId()));
    
        //Assert
        verify(einkaufslisteRepository).removeZutat(zutat);
        assertTrue(actual.isSuccessful());
    }

    private void givenEinkaufsliste(Einkaufsliste einkaufsliste) {
        when(einkaufslisteRepository.getEinkaufsliste()).thenReturn(einkaufsliste);
    }

    @Test
    public void should_do_nothing_when_zutat_is_already_removed() {
        //Arrange
        var someNonExistantId = 55555555L;
        givenEinkaufsliste(Einkaufsliste.CreateEmpty());

        //Act
        var actual = testee.execute(new RemoveZutatCommand(someNonExistantId));

        //Assert
        verify(einkaufslisteRepository, times(0)).removeZutat(any());
        assertTrue(actual.isSuccessful());
    }
}