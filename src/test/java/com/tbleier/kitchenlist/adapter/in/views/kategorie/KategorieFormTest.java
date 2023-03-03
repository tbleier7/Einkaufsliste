package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KategorieFormTest {

    @Mock
    private CommandService<SaveKategorieCommand> saveKategorieCommandService;

    @Mock
    private CommandService<DeleteKategorieCommand> deleteKategorieCommandService;

    private KategorieModelMapper mapper;

    @Captor
    ArgumentCaptor<SaveKategorieCommand> saveKategorieCommandCaptor;

    @Captor
    ArgumentCaptor<DeleteKategorieCommand> deleteKategorieCommandCaptor;

    private KategorieForm testee;

    @BeforeEach
    public void setUp() {
        mapper = KategorieModelMapper.INSTANCE;
        testee = new KategorieForm(new KategorieModel(), saveKategorieCommandService, deleteKategorieCommandService, mapper);
    }
    
    @Test
    public void Should_save_kategorie_on_save_click() {
        //Arrange
        var expectedKategorie = new Kategorie("Gemuese");
        testee.name.setValue(expectedKategorie.getName());
    
        //Act
        testee.save.click();
    
        //Assert
        verify(saveKategorieCommandService).execute(saveKategorieCommandCaptor.capture());
        var saveKategorieCommand = saveKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorie, saveKategorieCommand.getKategorie());
    }

    @Test
    public void should_delete_kategorie_on_delete_click() {
        givenCurrentKategorie("Gemüse");
        var expectedKategorie = new Kategorie("Gemüse");

        //Act
        testee.delete.click();

        //Assert
        verify(deleteKategorieCommandService).execute(deleteKategorieCommandCaptor.capture());
        var deleteKategorieCommand = deleteKategorieCommandCaptor.getValue();
        assertEquals(expectedKategorie, deleteKategorieCommand.getKategorie());
    }

    private void givenCurrentKategorie(String name) {
        var model = new KategorieModel();
        model.setName(name);
        testee.setKategorieModel(model);
    }

}