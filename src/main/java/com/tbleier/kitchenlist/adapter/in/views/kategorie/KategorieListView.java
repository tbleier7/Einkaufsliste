package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
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
import java.util.stream.Stream;

@PageTitle("Kitchen List")
@Route(value = "kategorie", layout = MainLayout.class)
public class KategorieListView extends VerticalLayout {

    private final KategorieFormFactory kategorieFormFactory;
    private final QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService;
    private final KategorieModelMapper mapper;
    Grid<KategorieModel> grid = new Grid<>(KategorieModel.class);
    KategorieForm kategorieForm;
    Button addKategorieButton;

    @Autowired
    public KategorieListView(KategorieFormFactory kategorieFormFactory,
                             QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService,
                             KategorieModelMapper mapper) {
        this.kategorieFormFactory = kategorieFormFactory;
        this.listAllKategorienQueryService = listAllKategorienQueryService;
        this.mapper = mapper;
        addClassName("kategorie-list-view");
        setSizeFull();

        configureGrid();
        configureKategorieForm();

        add(getToolbar(), getContent());
        closeEditor();
    }

    private void closeEditor() {
        kategorieForm.setKategorieModel(null);
        kategorieForm.setVisible(false);
        removeClassName("editing");
    }

    private Stream<KategorieModel> loadKategorien(int offset, int limit) {

        var kategorien = listAllKategorienQueryService.execute(new ListAllKategorienQuery());
        if(kategorien.isEmpty())
            return Stream.<KategorieModel>builder().build();

        var models = mapper.kategorieToModel(kategorien);

        return models.stream();
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
        kategorieForm.addListener(SaveKategorieEvent.class, this::reloadKategorien);
    }

    private <T extends ComponentEvent<?>> void reloadKategorien(SaveKategorieEvent event) {
        grid.getDataProvider().refreshAll();
    }

    private Component getToolbar() {

        addKategorieButton = new Button("Kategorie hinzufügen");
        addKategorieButton.addClickListener(click -> openKategorieForm());

        HorizontalLayout toolbar = new HorizontalLayout(addKategorieButton);
        toolbar.addClassName("kategorie-toolbar");

        return toolbar;
    }

    private void openKategorieForm() {
        grid.asSingleSelect().clear();
        editKategorie(new KategorieModel());
    }

    private void configureGrid() {

        grid.addClassName("kategorie-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editKategorie(e.getValue()));
        grid.setItems(vaadinQuery -> loadKategorien(vaadinQuery.getOffset(), vaadinQuery.getLimit()));
    }

    private void editKategorie(KategorieModel kategorieModel) {

        if(kategorieModel == null) {
            closeEditor();
            return;
        }

        kategorieForm.setKategorieModel(kategorieModel);
        kategorieForm.setVisible(true);
        addClassName("editing");
    }

}
