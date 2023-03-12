package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Einkaufsliste")
@Route(value = "einkaufsliste", layout = MainLayout.class)
public class EinkaufslisteListView extends VerticalLayout {

    Grid<EinkaufslistenPositionDTO> grid = new Grid<>(EinkaufslistenPositionDTO.class);
    Button addArtikelButton;

    @Autowired
    public EinkaufslisteListView() {
        addClassName("einkaufsliste-list-view");
        setSizeFull();

        add(getToolbar(), getToBuyTabSheet());
    }

    private TabSheet getToBuyTabSheet() {
        TabSheet toBuyTabsheet = new TabSheet();
        configureGrid();

        toBuyTabsheet.setSizeFull();
        toBuyTabsheet.add("Offen",
                grid);
        return toBuyTabsheet;
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
    }
}

