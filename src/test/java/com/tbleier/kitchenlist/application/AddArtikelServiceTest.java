package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.commands.AddArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddArtikelServiceTest {

    @Mock
    private ArtikelRepository artikelRepository;

    private AddArtikelService testee;

    @BeforeEach
    public void setUp() {
        testee = new AddArtikelService(artikelRepository);
    }
    
    @Test
    public void should_save_new_zutat_to_persistence() {
        //Arrange
        var zutat = new Artikel("someName", Einheit.Stueck, new Kategorie("someCategory"));

        //Act
        testee.execute(new AddArtikelCommand(zutat));
    
        //Assert
        verify(artikelRepository).save(zutat);
    }
}