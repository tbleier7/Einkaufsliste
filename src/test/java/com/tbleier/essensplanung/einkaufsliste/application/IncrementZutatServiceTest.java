package com.tbleier.essensplanung.einkaufsliste.application;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tbleier.essensplanung.einkaufsliste.application.IncrementZutatService;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.IncrementZutatCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IncrementZutatServiceTest {

    @Mock
    private EinkaufslisteRepository einkaufslisteRepository;

    private IncrementZutatService testee;
    
    @BeforeEach
    public void setUp() {
        testee = new IncrementZutatService(einkaufslisteRepository);
    }
    
    @Test
    public void should_increment_menge_via_einkaufsliste_and_save_it() {
        //Arrange
        var zutatId = 123L;
        givenZutatWithIdandMenge(zutatId, 2);

        //Act
        testee.execute(new IncrementZutatCommand(zutatId));
    
        //Assert
        verify(einkaufslisteRepository).saveZutat(argThat(zutat -> zutat.getMenge() == 3));
    }

    private void givenZutatWithIdandMenge(long id, int menge) {

        var einkaufsliste = Einkaufsliste.CreateEmpty();
        einkaufsliste.addExistingZutat(id, new Artikel(14L, "AnArtikel", Einheit.Stueck, new Kategorie(234L, "AKategorie")), menge);

        when(einkaufslisteRepository.getEinkaufsliste()).thenReturn(einkaufsliste);
    }
}