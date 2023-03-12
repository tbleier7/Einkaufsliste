package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddArtikelDialog extends Dialog {

    private final QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;
    Button cancelButton = new Button("Abbrechen");
    Button addButton = new Button("Hinzufügen");

    private List<ArtikelDTO> artikelDTOs;

    ComboBox<ArtikelDTO> artikelDTOComboBox;

    @Autowired
    public AddArtikelDialog(QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService) {
        this.listArtikelQueryService = listArtikelQueryService;

        artikelDTOComboBox = new ComboBox<>("Artikel");
        artikelDTOComboBox.setItemLabelGenerator(ArtikelDTO::getName);

        artikelDTOs = listArtikelQueryService.execute(new ListArtikelQuery());
        artikelDTOComboBox.setItems(artikelDTOs);

        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(artikelDTOComboBox);

        cancelButton.addClickListener(event -> this.close());

        add(horizontalLayout, addButton, cancelButton);
    }

    public List<ArtikelDTO> getArtikelDTOs() {
        return artikelDTOs;
    }
}
