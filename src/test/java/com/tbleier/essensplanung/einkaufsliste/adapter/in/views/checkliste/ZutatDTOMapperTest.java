package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.checkliste;

import com.tbleier.essensplanung.einkaufsliste.application.ZutatenDTOMapper;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.einkaufsliste.application.ports.ZutatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ZutatDTOMapperTest {

    private ZutatenDTOMapper testee;

    @BeforeEach
    public void setUp() {
        testee = ZutatenDTOMapper.INSTANCE;
    }

    @Test
    public void should_map_zutat_to_zutat_dto() {
        //Arrange
        var einkaufsliste = Einkaufsliste.CreateEmpty();
        var zutat = einkaufsliste.addExistingZutat(15L, new Artikel(12L, "MyArtikel", Einheit.Stueck, new Kategorie(124L, "Gemüse")), 3);

//        var zutat = Zutat.CreateWithId(15L, new Artikel(12L, "MyArtikel", Einheit.Stueck, new Kategorie(124L, "Gemüse")), 3, 1);
        var expectedZutatDto = new ZutatDTO(15L, 12L, "MyArtikel", 3);

        //Act
        var actual = testee.zutatToDTO(zutat);

        //Assert
        assertEquals(actual, expectedZutatDto);
    }
}
