package com.tbleier.kitchenlist.adapter.in.views.artikel;

import static org.junit.jupiter.api.Assertions.*;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArtikelModelMapperTest {
    
    private ArtikelModelMapper testee;
    
    @BeforeEach
    public void setUp() {
        testee = ArtikelModelMapper.INSTANCE;
    }

    @Test
    public void should_map_artikel() {
        //Arrange
        var expectedModel = new ArtikelModel("Zwiebeln", Einheit.Stueck, "Gemüse");

        //Act
        var actual = testee.artikelToModel(new Artikel(2 , "Zwiebeln", Einheit.Stueck, new Kategorie(1, "Gemüse")));

        //Assert
        assertEquals(expectedModel.getKategorie(), actual.getKategorie());
        assertEquals(expectedModel.getEinheit(), actual.getEinheit());
        assertEquals(expectedModel.getKategorie(), actual.getKategorie());
    }
}