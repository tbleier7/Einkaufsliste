package com.tbleier.kitchenlist.application;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.Assert.assertTrue;

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
        var zutat = new Zutat(new Artikel(23L, "Wurst", Einheit.Stueck, new Kategorie(324L, "Fleischwaren")), 3, 1);
        givenZutatExists(zutat);

        //Act
        var actual = testee.execute(new RemoveZutatCommand(23L));
    
        //Assert
        verify(einkaufslisteRepository).removeZutat(zutat);
        assertTrue(actual.isSuccessful());
    }

    private void givenZutatExists(Zutat zutat) {
        when(einkaufslisteRepository.findByArtikelId(anyLong())).thenReturn(Optional.of(zutat));
    }
}