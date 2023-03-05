package com.tbleier.kitchenlist.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListArtikelServiceTest {

    @Mock
    private ArtikelRepository artikelRepository;

    private ListArtikelService testee;
    private List<Artikel> artikel = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        testee = new ListArtikelService(artikelRepository);
    }

    @Test
    public void should_get_all_artikel_from_repository() {
        //Arrange
        givenTwoArtikel();

        //Act
        List<Artikel> actual = testee.execute(new ListArtikelQuery());

        //Assert
        assertEquals(2, actual.size());
    }

    private void givenTwoArtikel() {
        artikel.add(new Artikel("Zwiebeln", Einheit.Stueck, new Kategorie("Gemüse")));
        artikel.add(new Artikel("Gelbwurst", Einheit.Stueck, new Kategorie("Wurst")));

        when(artikelRepository.findAll()).thenReturn(artikel);
    }
}