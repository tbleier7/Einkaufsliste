package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.vaadin.flow.data.provider.Query;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtikelFormTest {

    @Mock
    private CommandService<SaveArtikelCommand> addRezeptCommandCommandService;
    @Mock
    private QueryService<ListAllKategorienQuery, List<Kategorie>> listKategorienQueryService;
    @Captor
    ArgumentCaptor<SaveArtikelCommand> addZutatCommandCaptor;

    private List<String> kategorieNames;

    private ArtikelForm testee;
    private AtomicBoolean saveEventWasFired;

    @BeforeEach
    public void setUp() {

        givenTwoKategorien();
        CreateTestee();
    }

    private void CreateTestee() {
        testee =  new ArtikelForm(new ArtikelModel(),
                addRezeptCommandCommandService, listKategorienQueryService);

        saveEventWasFired = new AtomicBoolean(false);
        testee.addListener(SaveArtikelEvent.class, e -> {
            saveEventWasFired.set(true);
        });
    }

    @Test
    public void should_show_all_kategorien_in_comboBox() {
        //Act
        List<String> actual = testee.kategorie.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertEquals(kategorieNames, actual);
    }

    private void givenTwoKategorien() {
        kategorieNames = List.of("Gemüse", "Fleisch");
        when(listKategorienQueryService.execute(any()))
                .thenReturn(List.of(new Kategorie(0, "Gemüse"), new Kategorie(1, "Fleisch")));
    }

    //TODO: überarbeiten
//    @Test
//    public void should_add_a_new_artikel_on_save_click() {
//        //Arrange
//        var expectedArtikel = new Artikel("Zwiebeln", Einheit.Stueck, new Kategorie(1, kategorieNames.get(1)));
//
//        testee.name.setValue(expectedArtikel.getName());
//        testee.einheit.setValue(expectedArtikel.getEinheit());
//        testee.kategorie.setValue(kategorieNames.get(1));
//
//        //Act
//        testee.save.click();
//
//        //Assert
//        verify(addRezeptCommandCommandService).execute(addZutatCommandCaptor.capture());
//        var addZutatCommand = addZutatCommandCaptor.getValue();
//        assertEquals(expectedArtikel, addZutatCommand.getArtikel());
//
//        assertEquals(true, saveEventWasFired.get());
//    }
}