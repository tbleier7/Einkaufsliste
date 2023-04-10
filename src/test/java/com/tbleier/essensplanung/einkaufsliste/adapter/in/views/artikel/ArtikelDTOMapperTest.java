package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel;

import com.tbleier.essensplanung.application.domain.Artikel;
import com.tbleier.essensplanung.application.domain.Einheit;
import com.tbleier.essensplanung.application.domain.Kategorie;
import com.tbleier.essensplanung.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel.ArtikelDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ArtikelDTOMapperTest {
    
    private ArtikelDTOMapper testee;
    
    @BeforeEach
    public void setUp() {
        testee = ArtikelDTOMapper.INSTANCE;
    }

    @Test
    public void should_map_artikel() {
        //Arrange
        var expectedModel = new ArtikelDTO(2, "Zwiebeln", Einheit.Stueck, "Gemüse");
        Artikel artikel = new Artikel(2, "Zwiebeln", Einheit.Stueck, new Kategorie(6, "Gemüse"));

        //Act
        var actual = testee.artikelToModel(artikel);

        //Assert
        assertEquals(expectedModel, actual);
    }
}