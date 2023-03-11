package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
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
        var actual = testee.execute(new ListAllKategorienQuery());

        //Assert
        assertEquals(expectedKategorien, actual);
    }

    private void GivenKategorien(List<Kategorie> expectedKategorien) {
        when(repository.findAll()).thenReturn(expectedKategorien);
    }
}