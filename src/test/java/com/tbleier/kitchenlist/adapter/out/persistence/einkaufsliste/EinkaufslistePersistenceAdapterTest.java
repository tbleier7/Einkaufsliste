package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.adapter.out.persistence.JpaMapperConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.PersistenceConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaEntity;
import com.tbleier.kitchenlist.adapter.out.persistence.kategorie.KategorieJpaEntity;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = EinkaufslistePersistenceAdapterTest.DataSourceInitializer.class,
        classes = {EinkaufslistePersistenceAdapter.class,
                PersistenceConfig.class,
                JpaMapperConfig.class})
class EinkaufslistePersistenceAdapterTest {

    @Container
    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:15.2-alpine");
    
    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, "spring.datasource.url=" + database.getJdbcUrl(), "spring.datasource.username=" + database.getUsername(), "spring.datasource.password=" + database.getPassword());
        }
    }

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

        //Act
        var savedId = testee.save(new Zutat(new Artikel(artikel.getId(), artikel.getName(),
                artikel.getEinheit(), new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 1, 1));
    
        //Assert
        var actual = entityManager.find(ZutatJpaEntity.class, savedId);
        assertNotNull(actual);
    }

    @Test
    public void should_list_all_zutaten() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        ArtikelJpaEntity artikel2 = CreateArtikel("SomeOtherArtikel", kategorieJpaEntity);

        entityManager.persist(new ZutatJpaEntity(artikel1, 2, 1));
        entityManager.persist(new ZutatJpaEntity(artikel2, 1, 2));

        //Act
        var actual = testee.listZutaten();

        //Assert
        assertEquals(actual.size(), 2);
    }

    @Test
    public void should_return_an_empty_list_when_einkaufsliste_is_empty() {
        //Act
        var actual = testee.getEinkaufsliste();

        //Assert
        assertEquals(actual.getZutaten(), Collections.emptyList());
    }
    
    @Test
    public void should_find_a_zutat_by_its_artikel() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        entityManager.persist(new ZutatJpaEntity(artikel1, 2, 1));

        //Act
        var actual = testee.findByArtikelId(artikel1.getId());
    
        //Assert
        assertTrue(actual.isPresent());
    }

    @Test
    public void should_remove_a_zutat() {
        //Arrange
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);
        var zutatJpaEntity = entityManager.persist(new ZutatJpaEntity(artikel1, 2, 1));

        //Act
        testee.removeZutat(new Zutat(
                new Artikel(artikel1.getId(), artikel1.getName(), artikel1.getEinheit(),
                    new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 2, 1)
        );

        //Assert
        var actual = entityManager.find(ZutatJpaEntity.class, zutatJpaEntity.getId());
        assertNull(actual);
    }
    
    @Test
    public void should_save_einkaufsliste() {
        //Arrange
        var einkaufsliste = new Einkaufsliste();
        ArtikelJpaEntity artikel1 = CreateArtikel("SomeArtikel", kategorieJpaEntity);

        einkaufsliste.addZutat(new Artikel(artikel1.getId(), artikel1.getName(), artikel1.getEinheit(),
        new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName())), 2);

        //Act
        testee.save(einkaufsliste);
    
        //Assert
        assertEquals(1, testee.getEinkaufsliste().getZutaten().size());
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