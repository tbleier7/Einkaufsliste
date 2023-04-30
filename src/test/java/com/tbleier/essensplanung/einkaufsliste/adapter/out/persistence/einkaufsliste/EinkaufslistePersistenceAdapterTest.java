package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.einkaufsliste;

import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.PersistenceAdapterTest;
import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.artikel.ArtikelJpaEntity;
import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie.KategorieJpaEntity;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Einkaufsliste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
class EinkaufslistePersistenceAdapterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EinkaufslistePersistenceAdapter testee;

    private KategorieJpaEntity kategorieJpaEntity;

    @BeforeEach
    public void setUp() {
        var kategorie = new KategorieJpaEntity();
        kategorie.setName("Gemüse");
        kategorieJpaEntity = entityManager.persist(kategorie);
    }

    @Test
    public void should_save_a_zutat() {
        //Arrange
        ArtikelJpaEntity artikel = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        var einkaufsliste = Einkaufsliste.CreateWithZutaten(Collections.emptyList());

        //Act
        var savedId = testee.saveZutat(einkaufsliste.addZutat(new Artikel(artikel.getId(),
                artikel.getName(),
                artikel.getEinheit(),
                new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())),
                1));

        //Assert
        var actual = entityManager.find(ZutatJpaEntity.class, savedId);
        assertNotNull(actual);
    }

    @Test
    public void should_list_all_zutaten_sorted_by_einkaufslisteIndex() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        ArtikelJpaEntity artikel2 = CreateArtikel("SomeOtherArtikel", kategorieJpaEntity);

        var zutat1 = entityManager.persist(new ZutatJpaEntity(artikel1, 2, 1));
        var zutat2 = entityManager.persist(new ZutatJpaEntity(artikel2, 1, 0));

        //Act
        var actual = testee.getEinkaufsliste();

        //Assert
        assertEquals(actual.getZutaten().size(), 2);
        assertEquals(actual.getZutaten().get(0).getId(), zutat2.getId());
        assertEquals(actual.getZutaten().get(1).getId(), zutat1.getId());
    }

    @Test
    public void should_return_an_empty_list_when_einkaufsliste_is_empty() {
        //Act
        var actual = testee.getEinkaufsliste();

        //Assert
        assertEquals(actual.getZutaten(), Collections.emptyList());
    }

    @Test
    public void should_remove_a_zutat() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        var zutatJpaEntity = entityManager.persist(new ZutatJpaEntity(artikel1, 2, 1));

        //Act
        testee.removeZutat(Einkaufsliste.CreateEmpty().addExistingZutat(zutatJpaEntity.getId(),
                new Artikel(artikel1.getId(), artikel1.getName(), artikel1.getEinheit(),
                        new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 2));

        //Assert
        var actual = entityManager.find(ZutatJpaEntity.class, zutatJpaEntity.getId());
        assertNull(actual);
    }
    
    @Test
    public void should_save_whole_einkaufsliste() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        ArtikelJpaEntity artikel2 = CreateArtikel("SomeArtikel2", kategorieJpaEntity);

        var einkaufsliste = Einkaufsliste.CreateEmpty();

        einkaufsliste.addZutat(new Artikel(artikel1.getId(), artikel1.getName(), artikel1.getEinheit(),
        new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 2);
        einkaufsliste.addZutat(new Artikel(artikel2.getId(), artikel2.getName(), artikel2.getEinheit(),
                new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 2);

        //Act
        testee.save(einkaufsliste);
        var actual = testee.getEinkaufsliste();
    
        //Assert
        assertEquals(2, actual.getZutaten().size());
    }

    private ArtikelJpaEntity CreateArtikel(String someArtikel, KategorieJpaEntity kategorie) {
        var artikel = new ArtikelJpaEntity();
        artikel.setName("SomeArtikel");
        artikel.setEinheit(Einheit.Stueck);
        artikel.setKategorie(kategorie);

        artikel = entityManager.persist(artikel);
        return artikel;
    }
}