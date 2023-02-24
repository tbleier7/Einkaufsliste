package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KategorieListViewTest {
    @Mock
    private CommandService<SaveKategorieCommand> addKategorieCommandService;

    @Mock
    private QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService;

    private KategorieListView testee;

    @BeforeEach
    public void setUp() {

        var mapper = KategorieModelMapper.INSTANCE;

        var formFactory = new KategorieFormFactory(
                addKategorieCommandService,
                mapper);

        testee = new KategorieListView(formFactory,
                listAllKategorienQueryService,
                mapper);
    }
    
    @Test
    public void Should_show_all_kategorien() {
        //Arrange
        KategorieModel kategorieModel1 = new KategorieModel();
        kategorieModel1.setName("Gemüse");

        KategorieModel kategorieModel2 = new KategorieModel();
        kategorieModel2.setName("Fleisch");

        GivenKategorien(List.of(new Kategorie("Gemüse"), new Kategorie("Fleisch")));

        //Act
        var actual = testee.grid.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());

        //Assert
        assertThat(actual, hasSize(2));
        assertThat(actual, hasItems(kategorieModel1, kategorieModel2));
    }

    private void GivenKategorien(List<Kategorie> kategorien) {
        when(listAllKategorienQueryService.execute(any())).thenReturn(kategorien);
    }
}