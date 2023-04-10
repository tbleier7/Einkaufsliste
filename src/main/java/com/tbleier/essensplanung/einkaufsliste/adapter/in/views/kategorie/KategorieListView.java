package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie;

import com.tbleier.essensplanung.MainLayout;
import com.tbleier.essensplanung.einkaufsliste.application.ports.KategorieDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.QueryService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.queries.ListKategorienQuery;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteKategorieCommand;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@PageTitle("Kategorien")
@Route(value = "kategorie", layout = MainLayout.class)

public class KategorieListView extends VerticalLayout {

    private final KategorieFormFactory kategorieFormFactory;
    private final QueryService<ListKategorienQuery, List<KategorieDTO>> listAllKategorienQueryService;
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;
    private final KategorieModelMapper mapper;
    Grid<KategorieDTO> grid = new Grid<>(KategorieDTO.class);
    KategorieForm kategorieForm;
    Button addKategorieButton;

    private List<KategorieDTO> kategorieDTOS;

    @Autowired
    public KategorieListView(KategorieFormFactory kategorieFormFactory,
                             QueryService<ListKategorienQuery, List<KategorieDTO>> listAllKategorienQueryService,
                             CommandService<DeleteKategorieCommand> deleteKategorieCommandService,
                             KategorieModelMapper mapper) {
        this.kategorieFormFactory = kategorieFormFactory;
        this.listAllKategorienQueryService = listAllKategorienQueryService;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
        this.mapper = mapper;
        kategorieDTOS = new LinkedList<>();

        addClassName("kategorie-list-view");
        setSizeFull();
        configureKategorieForm();
        configureGrid();

        add(getToolbar(), getContent());
        closeEditor();

        grid.asSingleSelect().addValueChangeListener(event ->
                editKategorie(event.getValue()));
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
        content.addClassNames("kategorie-content", "gap-m");
        content.setSizeFull();

        return content;
    }

    public List<KategorieDTO> getKategorieModels() {
        return kategorieDTOS;
    }

    private void configureKategorieForm() {

        kategorieForm = kategorieFormFactory.create(new KategorieDTO());
        kategorieForm.addListener(CloseEvent.class, e -> closeEditor());
        kategorieForm.addListener(SaveKategorieEvent.class, e -> {
            closeEditor();
            updateKategorie(e.getModel());
        });
        kategorieForm.addListener(DeleteKategorieEvent.class, e -> {
            closeEditor();
            removeKategorie(e.getModel());
        });
    }

    public void updateKategorie(KategorieDTO model) {
        kategorieDTOS.removeIf(m -> m.getId() == model.getId());
        kategorieDTOS.add(model);
        grid.setItems(kategorieDTOS);
    }

    public void removeKategorie(KategorieDTO model) {
        kategorieDTOS.remove(model);
        grid.setItems(kategorieDTOS);
    }

    public void reloadKategorien() {
        kategorieDTOS = new ArrayList<>(listAllKategorienQueryService.execute(new ListKategorienQuery()));
        grid.setItems(kategorieDTOS);
    }

    private Component getToolbar() {

        addKategorieButton = new Button("Kategorie hinzufügen");
        addKategorieButton.addClickListener(click -> editKategorie(new KategorieDTO()));

        HorizontalLayout toolbar = new HorizontalLayout(addKategorieButton);
        toolbar.addClassName("kategorie-toolbar");

        return toolbar;
    }

    private void configureGrid() {

        grid.addClassName("kategorie-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        reloadKategorien();
    }

    private void editKategorie(KategorieDTO kategorieDTO) {

        if(kategorieDTO == null) {
            closeEditor();
            return;
        }

        kategorieForm.setKategorieModel(kategorieDTO);
        kategorieForm.setVisible(true);
        addClassName("editing");
    }

}
