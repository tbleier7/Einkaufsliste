package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@PageTitle("Kitchen List")
@Route(value = "kategorie", layout = MainLayout.class)
public class KategorieListView extends VerticalLayout {

    //TODO: Implementier die Liste als Liste als Lazy, die beim ersten get auf die Collection geladen wird, dann geht das Testen einfacher wahrscheinlich,
    // dann: implementier das Löschen einfach als remove aus der liste, die hinter der grid liegt, dann spart man sich das neuladen ;)

    private final KategorieFormFactory kategorieFormFactory;
    private final QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService;
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;
    private final KategorieModelMapper mapper;
    Grid<KategorieModel> grid = new Grid<>(KategorieModel.class);
    KategorieForm kategorieForm;
    Button addKategorieButton;
    private List<KategorieModel> kategorieModels;

    @Autowired
    public KategorieListView(KategorieFormFactory kategorieFormFactory,
                             QueryService<ListAllKategorienQuery, List<Kategorie>> listAllKategorienQueryService,
                             CommandService<DeleteKategorieCommand> deleteKategorieCommandService,
                             KategorieModelMapper mapper) {
        this.kategorieFormFactory = kategorieFormFactory;
        this.listAllKategorienQueryService = listAllKategorienQueryService;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
        this.mapper = mapper;
        kategorieModels = Collections.emptyList();

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
        kategorieForm.addListener(CloseEvent.class, e -> closeEditor());
        kategorieForm.addListener(SaveKategorieEvent.class, e -> {
            closeEditor();
            addKategorie(e.getModel());
        });
    }

    private void addKategorie(KategorieModel model) {
        kategorieModels.add(model);
        grid.setItems(kategorieModels);
    }

    private void removeKategorie(KategorieModel model) {
        kategorieModels.remove(model);
        grid.setItems(kategorieModels);
    }

    private void reloadKategorien() {
        var kategorien = listAllKategorienQueryService.execute(new ListAllKategorienQuery());
        kategorieModels = mapper.kategorieToModel(kategorien);
        grid.setItems(kategorieModels);
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

        this.grid.addComponentColumn(item -> {
            var deleteButton = new Button("  X  ");
            deleteButton.addClickListener(event -> deleteKategorie(item));
            return deleteButton;
        });
        reloadKategorien();
    }

    public void deleteKategorie(KategorieModel item) {
        var kategorie = mapper.modelToKategorie(item);
        var commandResult = deleteKategorieCommandService.execute(new DeleteKategorieCommand(kategorie));

        if(commandResult.isSuccessful()) {
            closeEditor();
            removeKategorie(item);
        }
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
