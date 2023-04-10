package com.tbleier.essensplanung.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.tbleier.essensplanung.adapter.in.views.artikel.ArtikelDTOMapper;
import com.tbleier.essensplanung.application.domain.Artikel;
import com.tbleier.essensplanung.application.domain.Einheit;
import com.tbleier.essensplanung.application.domain.Kategorie;
import com.tbleier.essensplanung.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.essensplanung.application.ports.out.ArtikelRepository;
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
    private List<ArtikelDTO> expectedArtikelDTOs = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        testee = new ListArtikelService(artikelRepository, ArtikelDTOMapper.INSTANCE);
    }

    @Test
    public void should_get_all_artikel_from_repository() {
        //Arrange
        givenTwoArtikel();

        //Act
        List<ArtikelDTO> actual = testee.execute(new ListArtikelQuery());

        //Assert
        assertEquals(expectedArtikelDTOs, actual);
    }

    private void givenTwoArtikel() {
        artikel.add(new Artikel(1, "Zwiebeln", Einheit.Stueck,new Kategorie(3, "Gemüse")));
        artikel.add(new Artikel(2, "Gelbwurst", Einheit.Stueck,new Kategorie(4, "Wurst")));

        expectedArtikelDTOs.add(new ArtikelDTO(1, "Zwiebeln", Einheit.Stueck, "Gemüse"));
        expectedArtikelDTOs.add(new ArtikelDTO(2, "Gelbwurst", Einheit.Stueck, "Wurst"));

        when(artikelRepository.findAll()).thenReturn(artikel);
    }
}