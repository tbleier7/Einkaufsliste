package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie;

import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.PersistenceAdapterTest;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.NonUniqueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@PersistenceAdapterTest
class KategoriePersistenceAdapterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private KategoriePersistenceAdapter testee;

    @Autowired
    private KategorieJpaMapper mapper;

    @Test
    public void should_persist_a_new_kategorie() {
        //Arrange
        var expectedKategorie = new Kategorie(0, "Gemüse");

        //Act
        var actual = testee.save(expectedKategorie);

        //Assert
        assertThat(actual, greaterThan(0L));
    }

    @Test
    public void should_update_a_kategorie() {
        //Arrange
        var kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName("OldName");
        entityManager.persist(kategorieJpaEntity);

        var updatedKategorie = mapper.jpaEntityToKategorie(kategorieJpaEntity);
        updatedKategorie.rename("NewName");

        //Act
        testee.save(updatedKategorie);
        var actual = testee.findByName("NewName");

        //Assert
        assertEquals(updatedKategorie, actual);
    }

    @Test
    public void should_find_a_kategorie_by_id() {
        //Arrange
        var kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName("OldName");
        entityManager.persist(kategorieJpaEntity);

        //Act
        var actual = testee.findById(kategorieJpaEntity.getId());

        //Assert
        assertNotEquals(actual, Optional.empty());
        assertEquals(kategorieJpaEntity.getId(), actual.get().getId());
        assertEquals(kategorieJpaEntity.getName(), actual.get().getName());
    }

    @Test
    public void should_find_all_kategorien() {
        //Arrange
        var kategorie1 = new KategorieJpaEntity();
        kategorie1.setName("Gemüse");

        var kategorie2 = new KategorieJpaEntity();
        kategorie2.setName("Fleisch");

        entityManager.persist(kategorie1);
        entityManager.persist(kategorie2);

        var expectedKategorien = List.of(new Kategorie(kategorie1.getId(), "Gemüse"),
                new Kategorie(kategorie2.getId(), "Fleisch"));

        //Act
        List<Kategorie> actual = testee.findAll();

        //Assert
        assertEquals(expectedKategorien, actual);
    }

    @Test
    public void should_keep_kategorie_name_unique() {
        //Arrange
        var kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName("doublette");

        entityManager.persist(kategorieJpaEntity);

        var kategorieJpaEntityDoublette = new Kategorie(0, "doublette");

        //Act && Assert
        var exception = Assertions.assertThrows(NonUniqueException.class, () -> {
            testee.save(kategorieJpaEntityDoublette);
        });

        assertThat(exception.getMessage(), is("Kategorie existiert bereits!"));
    }

    @Test
    public void should_delete_a_kategorie() {
        //Arrange
        var kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName("toDelete");
        entityManager.persist(kategorieJpaEntity);

        //Act
        testee.delete(kategorieJpaEntity.getId());

        //Assert
        var deletedKategorie = entityManager.find(KategorieJpaEntity.class, kategorieJpaEntity.getId());
        assertNull(deletedKategorie);
    }
}