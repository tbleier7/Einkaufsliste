package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.SaveArtikelService;
import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.KategorieRepository;
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