package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Rezept;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("list")
@Route(value = "")
public class RezeptListView extends VerticalLayout {

    Grid<Rezept> grid = new Grid<>(Rezept.class);
    TextField filterText = new TextField();

    public RezeptListView() {
        addClassName("rezept-list-view");
        setSizeFull();

        configureGrid();

        add(getToolbar(), grid);
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Rezepte nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addRezeptButton = new Button("Rezept hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("rezept-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("rezept-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
