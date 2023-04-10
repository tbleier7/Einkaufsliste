package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.KategorieDTO;
import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.essensplanung.application.domain.Kategorie;
import com.tbleier.essensplanung.application.ports.in.queries.ListKategorienQuery;
import com.tbleier.essensplanung.application.ports.out.KategorieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllKategorienServiceTest {

    @Mock
    private KategorieRepository repository;

    private FindAllKategorienService testee;

    @BeforeEach
    public void setUp() {
        testee = new FindAllKategorienService(repository, KategorieModelMapper.INSTANCE);
    }

    @Test
    public void Should_get_all_kategorien_from_repository() {
        //Arrange
        var FoundKategorien = List.of(new Kategorie(5, "Gemüse"), new Kategorie(6, "Fleisch"));
        GivenKategorien(FoundKategorien);

        var expectedKategorien = List.of(new KategorieDTO(5, "Gemüse"), new KategorieDTO(6, "Fleisch"));

        //Act
        var actual = testee.execute(new ListKategorienQuery());

        //Assert
        assertEquals(expectedKategorien, actual);
    }

    private void GivenKategorien(List<Kategorie> expectedKategorien) {
        when(repository.findAll()).thenReturn(expectedKategorien);
    }
}