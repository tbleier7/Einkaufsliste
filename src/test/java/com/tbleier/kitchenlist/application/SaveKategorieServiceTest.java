package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveKategorieServiceTest {

    @Mock
    private KategorieRepository kategorieRepository;
    private SaveKategorieService testee;

    @BeforeEach
    public void setUp() {
        testee = new SaveKategorieService(kategorieRepository);
    }

    @Test
    public void should_save_kategorie_to_repository() {
        //Act
        testee.execute(new SaveKategorieCommand(0, "myKategorie"));

        //Assert
        verify(kategorieRepository).save(argThat(k -> k.getName().equals("myKategorie")));
    }
}