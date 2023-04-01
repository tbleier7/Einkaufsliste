package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaEntity;
import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaMapper;
import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaMapperImpl;
import com.tbleier.kitchenlist.adapter.out.persistence.kategorie.KategorieJpaMapperImpl;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ZutatJpaMapperTest {

    private ZutatJpaMapper testee;

    @BeforeEach
    public void setUp() {
        testee = new ZutatJpaMapperImpl(new ArtikelJpaMapperImpl(new KategorieJpaMapperImpl()));
    }

    @Test
    public void should_map_zutat_to_jpaEntity() {
        //Arrange
        var einkaufsliste = Einkaufsliste.CreateEmpty();
        einkaufsliste.addZutat(new Artikel(34L, "Artischoken", Einheit.Stueck, new Kategorie(45L, "Gemüse")), 3);

        var secondZutat = einkaufsliste.addZutat(new Artikel(
                12L, "Gurke", Einheit.Stueck, new Kategorie(45L, "Gemüse")), 3);

        //Act
        ZutatJpaEntity actual = testee.zutatToJpaEntity(secondZutat);

        //Assert
        assertEquals(secondZutat.getId(), actual.getId());
        assertEquals(secondZutat.getArtikel().getId(), actual.getArtikel().getId());
        assertEquals(secondZutat.getMenge(), actual.getMenge());
        assertEquals(1, actual.getEinkaufslisteIndex());
    }

}