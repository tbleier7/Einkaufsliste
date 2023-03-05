package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
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
    private final QueryService<ListArtikelQuery, List<Artikel>> listArtikelQueryService;
    private final ArtikelModelMapper mapper;
    Grid<ArtikelModel> grid = new Grid<>(ArtikelModel.class);
    TextField filterText = new TextField();
    ArtikelForm artikelForm;
    Button addRezeptButton;
    private List<ArtikelModel> artikelModels;

    @Autowired
    public ArtikelListView(ArtikelFormFactory artikelFormFactory,
                           QueryService<ListArtikelQuery, List<Artikel>> listArtikelQueryService,
                           ArtikelModelMapper mapper) {
        this.artikelFormFactory = artikelFormFactory;
        this.listArtikelQueryService = listArtikelQueryService;
        this.mapper = mapper;
        addClassName("artikel-list-view");
        setSizeFull();

        configureGrid();
        configureRezeptForm();

        add(getToolbar(), getContent());
        closeEditor();
    }

    private void closeEditor() {
        artikelForm.setArtikelModel(null);
        artikelForm.setVisible(false);
        removeClassName("editing");
    }

    private void openArtikelEditor(ArtikelModel artikelModel) {
        artikelForm.setArtikelModel(artikelModel);
        artikelForm.setVisible(true);
        addClassName("editing");
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

        artikelForm = artikelFormFactory.create(new ArtikelModel());
        artikelForm.addListener(SaveArtikelEvent.class, e -> {
            closeEditor();
            addArtikel(e.getArtikelModel());
        });
    }

    private void addArtikel(ArtikelModel artikelModel) {
        artikelModels.add(artikelModel);
        grid.setItems(artikelModels);
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Artikel nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        addRezeptButton = new Button("Zutat hinzufügen");
        addRezeptButton.addClickListener(e -> openArtikelEditor(new ArtikelModel()));

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("artikel-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "einheit", "kategorie");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        var artikel = listArtikelQueryService.execute(new ListArtikelQuery());
        artikelModels = mapper.artikelToModel(artikel);
        grid.setItems(artikelModels);

        grid.asSingleSelect().addValueChangeListener(event ->
                openArtikelEditor(event.getValue()));
    }

}
