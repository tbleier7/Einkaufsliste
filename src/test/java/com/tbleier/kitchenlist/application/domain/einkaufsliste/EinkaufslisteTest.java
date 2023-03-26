package com.tbleier.kitchenlist.application.domain.einkaufsliste;

import static org.junit.jupiter.api.Assertions.*;

import com.beust.ah.A;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
class EinkaufslisteTest {

    private Einkaufsliste testee;

    @BeforeEach
    public void setUp() {
        testee = new Einkaufsliste();
    }

    @Test
    public void should_add_an_artikel_with_a_menge() {
        //Arrange
        Artikel artikel = CreateArtikel("Suppengemüse", "Gemüse");

        //Act
        testee.addZutat(artikel, 3);
        var actual = testee.getZutaten();

        //Assert
        assertEquals(1, actual.size());
    }
    
    @Test
    public void should_move_zutaten() {
        //Arrange
        Artikel suppengemuese = CreateArtikel("Suppengemüse", "SomeKategorie");
        Artikel kartoffeln = CreateArtikel("Kartoffeln", "SomeOtherKategorie");
        Artikel zwiebeln = CreateArtikel("Zwiebeln", "SomeOtherKategorie");

        testee.addZutat(suppengemuese, 3);
        testee.addZutat(kartoffeln, 4);
        testee.addZutat(zwiebeln, 2);

        //Act
        testee.moveZutatToIndex(zwiebeln.getId(), 0);
        testee.getZutaten();

        //Assert
        assertEquals(zwiebeln.getId(), testee.getZutaten().get(0).getArtikel().getId());
        assertEquals(suppengemuese.getId(), testee.getZutaten().get(1).getArtikel().getId());
    }

    private Artikel CreateArtikel(String artikelName, String kategorieName) {
        return new Artikel(new Random().nextLong(), artikelName, Einheit.Stueck,
                new Kategorie(new Random().nextLong(), kategorieName));
    }
}