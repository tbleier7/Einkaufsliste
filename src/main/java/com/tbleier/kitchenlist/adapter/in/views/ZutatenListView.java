package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Zutat;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("list")
@Route(value = "")
public class ZutatenListView extends VerticalLayout {

    Grid<Zutat> grid = new Grid<>(Zutat.class);
    TextField filterText = new TextField();
    ZutatenForm zutatenForm;

    public ZutatenListView() {
        addClassName("zutaten-list-view");
        setSizeFull();

        configureGrid();
        configureRezeptForm();

        add(getToolbar(), getContent());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, zutatenForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, zutatenForm);
        content.addClassName("zutaten-content");
        content.setSizeFull();

        return content;
    }

    private void configureRezeptForm() {
        zutatenForm = new ZutatenForm(List.of(new Kategorie("Gemüse")));
        zutatenForm.setWidth("25em");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Zutaten nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addRezeptButton = new Button("Zutat hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("zutaten-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("zutaten-grid");
        grid.setSizeFull();
        grid.setColumns("name","einheit","kategorie");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
