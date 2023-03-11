package com.tbleier.kitchenlist.adapter.out.persistence.artikel;

import com.tbleier.kitchenlist.adapter.out.persistence.JpaMapperConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.PersistenceConfig;
import com.tbleier.kitchenlist.adapter.out.persistence.kategorie.KategorieJpaEntity;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = ArtikelPersistenceAdapterTest.DataSourceInitializer.class,
        classes = {ArtikelPersistenceAdapter.class,
                PersistenceConfig.class,
                JpaMapperConfig.class})
public class ArtikelPersistenceAdapterTest {

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
    private ArtikelPersistenceAdapter testee;

    private Kategorie alreadySavedKategorie;


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
        var actual = testee.findById(id);

        //Assert
        assertEquals(false, actual.isEmpty());
        assertEquals(expectedArtikel.getKategorie(), actual.get().getKategorie());
        assertEquals(expectedArtikel.getEinheit(), actual.get().getEinheit());
        assertEquals(expectedArtikel.getKategorie(), actual.get().getKategorie());
    }

    private void givenAKategorie(String name) {
        var kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName(name);
        kategorieJpaEntity = entityManager.persist(kategorieJpaEntity);

        alreadySavedKategorie = new Kategorie(kategorieJpaEntity.getId(), kategorieJpaEntity.getName());
    }
}
