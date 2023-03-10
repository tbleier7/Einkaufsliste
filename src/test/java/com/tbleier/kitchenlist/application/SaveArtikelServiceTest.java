package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveArtikelServiceTest {

    @Mock
    private ArtikelRepository artikelRepository;

    private SaveArtikelService testee;

    @BeforeEach
    public void setUp() {
        testee = new SaveArtikelService(artikelRepository);
    }
    
    @Test
    public void should_save_new_zutat_to_persistence() {
        //Arrange
        var zutat = new Artikel(1, "someName", Einheit.Stueck, new Kategorie(8, "someCategory"));

        //Act
        testee.execute(new SaveArtikelCommand(zutat));
    
        //Assert
        verify(artikelRepository).save(zutat);
    }
}