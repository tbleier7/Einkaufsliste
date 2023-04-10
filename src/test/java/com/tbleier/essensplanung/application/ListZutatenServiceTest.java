package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.domain.Artikel;
import com.tbleier.essensplanung.application.domain.Einheit;
import com.tbleier.essensplanung.application.domain.Kategorie;
import com.tbleier.essensplanung.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.application.ports.in.queries.ListZutatenQuery;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListZutatenServiceTest {

    @Mock
    private EinkaufslisteRepository einkaufslisteRepository;

    private ListZutatenService testee;
    
    @BeforeEach
    public void setUp() {
        testee = new ListZutatenService(einkaufslisteRepository, ZutatenDTOMapper.INSTANCE);
    }
    
    @Test
    public void should_list_all_zutaten_from_repository() {
        //Arrange
        givenTwoZutatenInEinkaufsliste();

        //Act
        var actual = testee.execute(new ListZutatenQuery());
    
        //Assert
        Assert.assertEquals(actual.size(), 2);
    }

    private void givenTwoZutatenInEinkaufsliste() {

        var einkaufsliste = Einkaufsliste.CreateWithZutaten(Collections.emptyList());
        einkaufsliste.addZutat(new Artikel(12L, "Gelbwurst", Einheit.Gramm, new Kategorie(323L, "Wurst")), 2);
        einkaufsliste.addZutat(new Artikel(12L, "Paprikawurst", Einheit.Gramm, new Kategorie(323L, "Wurst")), 4);

        when(einkaufslisteRepository.getEinkaufsliste())
                .thenReturn(einkaufsliste);
    }
}