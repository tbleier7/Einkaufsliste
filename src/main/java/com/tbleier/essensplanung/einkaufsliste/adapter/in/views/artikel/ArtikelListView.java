package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel;

import com.tbleier.essensplanung.MainLayout;
import com.tbleier.essensplanung.einkaufsliste.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.QueryService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.queries.ListArtikelQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Artikel")
@Route(value = "artikel", layout = MainLayout.class)
public class ArtikelListView extends VerticalLayout {

    private final ArtikelFormFactory artikelFormFactory;
    private final QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;
    private final ArtikelDTOMapper mapper;
    Grid<ArtikelDTO> grid = new Grid<>(ArtikelDTO.class);
    ArtikelForm artikelForm;
    Button addRezeptButton;

    private List<ArtikelDTO> artikelDTOs;

    @Autowired
    public ArtikelListView(ArtikelFormFactory artikelFormFactory,
                           QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService,
                           ArtikelDTOMapper mapper) {
        this.artikelFormFactory = artikelFormFactory;
        this.listArtikelQueryService = listArtikelQueryService;
        this.mapper = mapper;
        addClassName("artikel-list-view");
        setSizeFull();

        configureGrid();
        configureRezeptForm();

        add(getToolbar(), getContent());
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event ->
                openArtikelEditor(event.getValue()));
    }

    public List<ArtikelDTO> getArtikelDTOs() {
        return artikelDTOs;
    }

    public void addArtikel(ArtikelDTO artikelDTO) {
        artikelDTOs.removeIf(m -> m.getId() == artikelDTO.getId());
        artikelDTOs.add(artikelDTO);
        grid.setItems(artikelDTOs);
    }

    private void closeEditor() {
        artikelForm.setArtikelModel(null);
        artikelForm.setVisible(false);
        removeClassName("editing");
    }

    private void openArtikelEditor(ArtikelDTO artikelDTO) {

        if(artikelDTO == null) {
            closeEditor();
            return;
        }

        artikelForm.setArtikelModel(artikelDTO);
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

        artikelForm = artikelFormFactory.create(new ArtikelDTO());
        artikelForm.addListener(SaveArtikelEvent.class, e -> {
            closeEditor();
            addArtikel(e.getArtikelModel());
        });
        artikelForm.addListener(DeleteArtikelEvent.class, e -> {
            removeArtikel(e.getArtikelModel());
            closeEditor();
        });
        artikelForm.addListener(CloseEvent.class, e -> closeEditor());
    }

    private Component getToolbar() {

        addRezeptButton = new Button("Artikel hinzufügen");
        addRezeptButton.addClickListener(e -> openArtikelEditor(new ArtikelDTO()));

        HorizontalLayout toolbar = new HorizontalLayout(addRezeptButton);
        toolbar.addClassName("artikel-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "einheit", "kategorie");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        artikelDTOs = new ArrayList<>(listArtikelQueryService.execute(new ListArtikelQuery()));
        grid.setItems(artikelDTOs);
    }

    public void removeArtikel(ArtikelDTO artikelModel) {
        artikelDTOs.remove(artikelModel);
        grid.setItems(artikelDTOs);
    }
}
