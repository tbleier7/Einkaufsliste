package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class EinkaufslisteListViewTest {

    @Mock
    private QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> einkaufsListeQueryService;

    private EinkaufslisteListView testee;

    @BeforeEach
    public void setUp() {
        givenTwoEntriesForEinkaufsliste();
        testee = new EinkaufslisteListView(einkaufsListeQueryService);
    }

    @Test
    public void should_show_all_einkaufslistenpositionen() {
        //Assert
        assertEquals(2, testee.getPositionDTOs().size());
    }

    private void givenTwoEntriesForEinkaufsliste() {
        when(einkaufsListeQueryService.execute(any())).thenReturn(List.of(new EinkaufslistenPositionDTO(),
                new EinkaufslistenPositionDTO()));
    }
}