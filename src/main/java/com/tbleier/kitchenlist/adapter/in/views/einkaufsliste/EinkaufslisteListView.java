package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Einkaufsliste")
@Route(value = "einkaufsliste", layout = MainLayout.class)
public class EinkaufslisteListView extends VerticalLayout {

    private final QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> einkaufsListeQueryService;
    Grid<EinkaufslistenPositionDTO> grid = new Grid<>(EinkaufslistenPositionDTO.class);

    private List<EinkaufslistenPositionDTO> positionDTOs = new ArrayList<>();
    Button addArtikelButton;

    final AddArtikelDialog addArtikelDialog;

    @Autowired
    public EinkaufslisteListView(QueryService<ListEinkaufslisteQuery,
            List<EinkaufslistenPositionDTO>> einkaufsListeQueryService,
            AddArtikelDialog addArtikelDialog) {
        this.einkaufsListeQueryService = einkaufsListeQueryService;
        this.addArtikelDialog = addArtikelDialog;
        addClassName("einkaufsliste-list-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private Component getToolbar() {

        addArtikelButton = new Button("Eintrag hinzufügen");
        addArtikelButton.addClickListener(event -> addArtikelDialog.open());

        HorizontalLayout toolbar = new HorizontalLayout(addArtikelButton);
        toolbar.addClassName("einkaufsliste-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();


        var checkBoxColumn = grid.addComponentColumn(item -> {
            Checkbox checkbox = new Checkbox();
            return checkbox;
        });

        var decrecementButtonColumn = grid.addComponentColumn(item -> {
            var decrementAmountButton = new Button("-");
            return decrementAmountButton;
        });

        var increcementButtonColumn = grid.addComponentColumn(item -> {
            var incrementAmountButton = new Button("+");
            return incrementAmountButton;
        });

        var artikelNameColumn = grid.getColumnByKey("artikelName");
        var amountColumn = grid.getColumnByKey("amount");
        amountColumn.setTextAlign(ColumnTextAlign.CENTER);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.setColumnOrder(
                checkBoxColumn,
                artikelNameColumn,
                decrecementButtonColumn,
                amountColumn,
                increcementButtonColumn);

        positionDTOs = einkaufsListeQueryService.execute(new ListEinkaufslisteQuery());
        grid.setItems(positionDTOs);
    }

    public List<EinkaufslistenPositionDTO> getPositionDTOs() {
        return positionDTOs;
    }
}

