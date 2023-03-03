package com.tbleier.kitchenlist.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
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
        testee.execute(new DeleteKategorieCommand(new Kategorie("Gemüse")));
    
        //Assert
        verify(kategorieRepository).delete(argThat(kategorie -> kategorie.getName().equals("Gemüse")));
    }
}