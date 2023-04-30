package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.artikel;

import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.PersistenceAdapterTest;
import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie.KategorieJpaEntity;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
public class ArtikelPersistenceAdapterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArtikelPersistenceAdapter testee;

    private Kategorie alreadySavedKategorie;
    private KategorieJpaEntity kategorieJpaEntity;


    @BeforeEach
    public void setUp() {
        alreadySavedKategorie = null;
    }

    @Test
    public void should_persist_an_artikel() {
        //Arrange
        givenAKategorie("Gemüse");
        var expectedArtikel = new Artikel(0, "someArtikel", Einheit.Stueck, alreadySavedKategorie);

        //Act
        var id = testee.save(expectedArtikel);

        //Assert
        var actual = entityManager.find(ArtikelJpaEntity.class, id);
        assertNotNull(actual);
        assertEquals(expectedArtikel.getKategorie().getId(), actual.getKategorie().getId());
        assertEquals(expectedArtikel.getEinheit(), actual.getEinheit());
        assertEquals(expectedArtikel.getName(), actual.getName());
    }

    @Test
    public void should_delete_an_artikel() {
        //Arrange
        givenAKategorie("Gemüse");
        var artikel = new ArtikelJpaEntity();
        artikel.setKategorie(kategorieJpaEntity);
        artikel = entityManager.persist(artikel);

        //Act
        testee.delete(artikel.getId());

        //Assert
        assertNull(entityManager.find(ArtikelJpaEntity.class, artikel.getId()));
    }

    private void givenAKategorie(String name) {
        kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName(name);
        kategorieJpaEntity = entityManager.persist(kategorieJpaEntity);

        alreadySavedKategorie = new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName());
    }
}
