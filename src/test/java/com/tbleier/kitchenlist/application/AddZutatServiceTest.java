package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.in.commands.AddZutatCommand;
import com.tbleier.kitchenlist.application.ports.out.ZutatPersistenceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddZutatServiceTest {

    @Mock
    private ZutatPersistenceAdapter zutatPersistenceAdapter;

    private AddZutatService testee;

    @BeforeEach
    public void setUp() {
        testee = new AddZutatService(zutatPersistenceAdapter);
    }
    
    @Test
    public void should_save_new_zutat_to_persistence() {
        //Arrange
        var zutat = new Zutat("someName", Einheit.Stueck, new Kategorie("someCategory"));

        //Act
        testee.execute(new AddZutatCommand(zutat));
    
        //Assert
        verify(zutatPersistenceAdapter).save(zutat);
    }
}