package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KategorieListViewTest {
    private KategorieListView testee;

    @Mock
    private CommandService<SaveKategorieCommand> addKategorieCommandService;


    @BeforeEach
    public void setUp() {
        testee = new KategorieListView(
                new KategorieFormFactory(
                        addKategorieCommandService, 
                        KategorieModelMapper.INSTANCE));
    }
    
    @Test
    @Disabled
    public void Should_show_all_kategorien() {
        //Arrange

        //Act
        var actual = testee.grid.getGenericDataView().getItems().toList();

        //Assert

    }
}