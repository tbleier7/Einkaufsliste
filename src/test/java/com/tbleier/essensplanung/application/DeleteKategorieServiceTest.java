package com.tbleier.essensplanung.application;

import static org.mockito.Mockito.verify;

import com.tbleier.essensplanung.application.ports.in.commands.DeleteKategorieCommand;
import com.tbleier.essensplanung.application.ports.out.KategorieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteKategorieServiceTest {
    
    @Mock
    private KategorieRepository kategorieRepository;
    
    private DeleteKategorieService testee;
    
    @BeforeEach
    public void setUp() {
        testee = new DeleteKategorieService(kategorieRepository);
    }
    
    @Test
    public void should_delete_kategorie() {
        //Arrange
    
        //Act
        testee.execute(new DeleteKategorieCommand(8));
    
        //Assert
        verify(kategorieRepository).delete(8);
    }
}