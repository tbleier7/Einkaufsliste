package com.tbleier.kitchenlist.adapter.out.persistence.kategorie;

import com.tbleier.kitchenlist.adapter.out.persistence.JpaMapperConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.PersistenceConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelPersistenceAdapter;
import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelPersistenceAdapterTest;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = KategoriePersistenceAdapterTest.DataSourceInitializer.class,
        classes = {KategoriePersistenceAdapter.class,
                JpaMapperConfig.class,
                PersistenceConfig.class})
class KategoriePersistenceAdapterTest {
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
    private KategoriePersistenceAdapter testee;

    @Test
    public void should_persist_a_kategorie() {
        //Arrange
        var expectedKategorie = new Kategorie("Gemüse");

        //Act
        testee.save(expectedKategorie);
        var actual = testee.findByName("Gemüse");

        //Assert
        assertEquals(expectedKategorie, actual);
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

        var expectedKategorien = List.of(new Kategorie("Gemüse"), new Kategorie("Fleisch"));

        //Act
        List<Kategorie> actual = testee.findAll();

        //Assert
        assertEquals(expectedKategorien, actual);
    }
}