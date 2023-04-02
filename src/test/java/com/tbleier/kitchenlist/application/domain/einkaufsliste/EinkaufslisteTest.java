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
        Artikel suppengemueseArtikel = CreateArtikel("Suppengemüse", "SomeKategorie");
        Artikel kartoffelnArtikel = CreateArtikel("Kartoffeln", "SomeOtherKategorie");
        Artikel zwiebelnArtikel = CreateArtikel("Zwiebeln", "SomeOtherKategorie");
        Artikel wurstArtikel = CreateArtikel("Wurst", "SomeOtherKategorie");

        var suppengemueseZutat = testee.addExistingZutat(14L, suppengemueseArtikel, 3);
        var kartoffelnZutat = testee.addExistingZutat(56L, kartoffelnArtikel, 4);
        var zwiebelnZutat = testee.addExistingZutat(70L, zwiebelnArtikel, 2);
        var wurstZutat = testee.addExistingZutat(99L, wurstArtikel, 6);

        //Act
        testee.moveZutatToIndex(suppengemueseZutat.getId(), 2);

        //Assert
        assertEquals(kartoffelnZutat.getId(), testee.getZutaten().get(0).getId());
        assertEquals(0, testee.getZutaten().get(0).getEinkaufslisteIndex());

        assertEquals(zwiebelnZutat.getId(), testee.getZutaten().get(1).getId());
        assertEquals(1, testee.getZutaten().get(1).getEinkaufslisteIndex());

        assertEquals(suppengemueseZutat.getId(), testee.getZutaten().get(2).getId());
        assertEquals(2, testee.getZutaten().get(2).getEinkaufslisteIndex());

        assertEquals(wurstZutat.getId(), testee.getZutaten().get(3).getId());
        assertEquals(3, testee.getZutaten().get(3).getEinkaufslisteIndex());
    }

    @Test
    public void should_move_second_to_third_zutaten() {
        //Arrange
        Artikel suppengemueseArtikel = CreateArtikel("Suppengemüse", "SomeKategorie");
        Artikel kartoffelnArtikel = CreateArtikel("Kartoffeln", "SomeOtherKategorie");
        Artikel zwiebelnArtikel = CreateArtikel("Zwiebeln", "SomeOtherKategorie");
        Artikel wurstArtikel = CreateArtikel("Wurst", "SomeOtherKategorie");

        var suppengemueseZutat = testee.addExistingZutat(14L, suppengemueseArtikel, 3);
        var kartoffelnZutat = testee.addExistingZutat(56L, kartoffelnArtikel, 4);
        var zwiebelnZutat = testee.addExistingZutat(70L, zwiebelnArtikel, 2);
        var wurstZutat = testee.addExistingZutat(99L, wurstArtikel, 6);

        //Act
        testee.moveZutatToIndex(kartoffelnZutat.getId(), 2);

        //Assert
        assertEquals(suppengemueseZutat.getId(), testee.getZutaten().get(0).getId());
        assertEquals(0, testee.getZutaten().get(0).getEinkaufslisteIndex());

        assertEquals(zwiebelnZutat.getId(), testee.getZutaten().get(1).getId());
        assertEquals(1, testee.getZutaten().get(1).getEinkaufslisteIndex());

        assertEquals(kartoffelnZutat.getId(), testee.getZutaten().get(2).getId());
        assertEquals(2, testee.getZutaten().get(2).getEinkaufslisteIndex());

        assertEquals(wurstZutat.getId(), testee.getZutaten().get(3).getId());
        assertEquals(3, testee.getZutaten().get(3).getEinkaufslisteIndex());
    }
    
    @Test
    public void should_increment_menge_of_a_zutat() {
        //Arrange
        var zutatId = 12L;
        testee.addExistingZutat(zutatId, CreateArtikel("AnArtikel", "Gemüse"), 2);

        //Act
        var actual = testee.incrementZutat(zutatId);

        //Assert
        assertEquals(actual.getMenge(), 3);
    }

    @Test
    public void should_decrement_menge_of_a_zutat() {
        //Arrange
        var zutatId = 12L;
        testee.addExistingZutat(zutatId, CreateArtikel("AnArtikel", "Gemüse"), 2);

        //Act
        var actual = testee.decrementZutat(zutatId);

        //Assert
        assertEquals(actual.getMenge(), 1);
    }

    private Artikel CreateArtikel(String artikelName, String kategorieName) {
        return new Artikel(new Random().nextLong(), artikelName, Einheit.Stueck,
                new Kategorie(new Random().nextLong(), kategorieName));
    }
}