package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.adapter.in.views.artikel.ArtikelForm;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
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
@Route(value = "kategorie", layout = MainLayout.class)
public class KategorieListView extends VerticalLayout {

    private final KategorieFormFactory kategorieFormFactory;
    Grid<Kategorie> grid = new Grid<>(Kategorie.class);
    TextField filterText = new TextField();
    KategorieForm kategorieForm;

    @Autowired
    public KategorieListView(KategorieFormFactory kategorieFormFactory) {
        this.kategorieFormFactory = kategorieFormFactory;
        addClassName("kategorie-list-view");
        setSizeFull();

        configureGrid();
        configureKategorieForm();

        add(getToolbar(), getContent());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, kategorieForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, kategorieForm);
        content.addClassName("kategorie-content");
        content.setSizeFull();

        return content;
    }

    private void configureKategorieForm() {

        kategorieForm = kategorieFormFactory.create(new KategorieModel());
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Kategorien nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addRezeptButton = new Button("Kategorie hinzufügen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("kategorie-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("kategorie-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
