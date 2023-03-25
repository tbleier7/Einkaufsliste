package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ZutatenDTOMapper;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.ZutatDTO;
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
        var zutat = new Zutat(new Artikel(12L, "MyArtikel", Einheit.Stueck, new Kategorie(124L, "Gemüse")), 3);
        var expectedZutatDto = new ZutatDTO(12L, "MyArtikel", 3);

        //Act
        var actual = testee.zutatToDTO(zutat);
    
        //Assert
        assertEquals(actual, expectedZutatDto);
    }
}
