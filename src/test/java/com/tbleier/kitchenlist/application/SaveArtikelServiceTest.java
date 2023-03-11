package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveArtikelServiceTest {

    @Mock
    private ArtikelRepository artikelRepository;

    @Mock
    private KategorieRepository kategorieRepository;

    private SaveArtikelService testee;

    @BeforeEach
    public void setUp() {
        testee = new SaveArtikelService(artikelRepository, kategorieRepository);
    }
    
    @Test
    public void should_save_new_zutat_to_persistence() {

        //Act
        testee.execute(new SaveArtikelCommand(1, "someName", Einheit.Stueck, "someCategory"));
    
        //Assert
        verify(artikelRepository).save(argThat(a -> a.getName() == "someName"));
    }
}