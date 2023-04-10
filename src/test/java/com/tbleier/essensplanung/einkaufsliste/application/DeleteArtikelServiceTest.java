package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.DeleteArtikelService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteArtikelServiceTest {

    @Mock
    private ArtikelRepository artikelRepository;

    private DeleteArtikelService testee;

    @BeforeEach
    public void setUp() {
        testee = new DeleteArtikelService(artikelRepository);
    }

    @Test
    public void should_delete_artikel_from_repository() {
        //Arrange

        //Act
        testee.execute(new DeleteArtikelCommand(1L));

        //Assert
        verify(artikelRepository).delete(1L);
    }
}