package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Kitchen List")
@Route(value = "", layout = MainLayout.class)
public class ArtikelListView extends VerticalLayout {

    private final ArtikelFormFactory artikelFormFactory;
    Grid<Artikel> grid = new Grid<>(Artikel.class);
    TextField filterText = new TextField();
    ArtikelForm artikelForm;

    @Autowired
    public ArtikelListView(ArtikelFormFactory artikelFormFactory) {
        this.artikelFormFactory = artikelFormFactory;
        addClassName("artikel-list-view");
        setSizeFull();

        configureGrid();
        configureRezeptForm();

        add(getToolbar(), getContent());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, artikelForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, artikelForm);
        content.addClassName("artikel-content");
        content.setSizeFull();

        return content;
    }

    private void configureRezeptForm() {

        artikelForm = artikelFormFactory.create(new Artikel("something", Einheit.Gramm, null),
                List.of(new Kategorie("Gemüse")));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Artikel nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addRezeptButton = new Button("Zutat hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("artikel-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "einheit", "kategorie");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
