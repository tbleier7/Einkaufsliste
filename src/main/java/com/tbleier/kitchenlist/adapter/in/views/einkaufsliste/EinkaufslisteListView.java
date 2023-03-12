package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
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

    @Autowired
    public EinkaufslisteListView(QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> einkaufsListeQueryService) {
        this.einkaufsListeQueryService = einkaufsListeQueryService;
        addClassName("einkaufsliste-list-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private Component getToolbar() {

        addArtikelButton = new Button("Eintrag hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout( addArtikelButton);
        toolbar.addClassName("einkaufsliste-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();

        grid.setColumns("artikelName", "amount");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        positionDTOs = einkaufsListeQueryService.execute(new ListEinkaufslisteQuery());
        grid.setItems(positionDTOs);
    }

    public List<EinkaufslistenPositionDTO> getPositionDTOs() {
        return positionDTOs;
    }
}

