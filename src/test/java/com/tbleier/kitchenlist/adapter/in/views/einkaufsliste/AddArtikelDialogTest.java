package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AddArtikelDialogTest {

    @Mock
    private QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;

    private AddArtikelDialog testee;

    @BeforeEach
    public void setUp() {
        givenTwoArtikel();
        testee = new AddArtikelDialog(listArtikelQueryService);
    }

    @Test
    public void should_show_all_artikel() {
        //Act
        List<ArtikelDTO> actual = testee.getArtikelDTOs();

        //Assert
        assertEquals(2, actual.size());
    }

    @Test
    public void should_close_on_cancel() {
        //Act
        testee.cancelButton.click();

        //Assert
        assertEquals(false, testee.isOpened());
    }

    private void givenTwoArtikel() {
        when(listArtikelQueryService.execute(any())).thenReturn(List.of(new ArtikelDTO(), new ArtikelDTO()));
    }
}