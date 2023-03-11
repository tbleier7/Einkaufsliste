package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
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

import java.util.ArrayList;
import java.util.List;

@PageTitle("Kitchen List")
@Route(value = "artikel", layout = MainLayout.class)
public class ArtikelListView extends VerticalLayout {

    private final ArtikelFormFactory artikelFormFactory;
    private final QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;
    private final ArtikelDTOMapper mapper;
    Grid<ArtikelDTO> grid = new Grid<>(ArtikelDTO.class);
    TextField filterText = new TextField();
    ArtikelForm artikelForm;
    Button addRezeptButton;
    private List<ArtikelDTO> artikelDTOS;

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
    }

    private void closeEditor() {
        artikelForm.setArtikelModel(null);
        artikelForm.setVisible(false);
        removeClassName("editing");
    }

    private void openArtikelEditor(ArtikelDTO artikelDTO) {
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
    }

    private void addArtikel(ArtikelDTO artikelDTO) {
        artikelDTOS.add(artikelDTO);
        grid.setItems(artikelDTOS);
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Artikel nach Namen filtern...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        addRezeptButton = new Button("Zutat hinzufügen");
        addRezeptButton.addClickListener(e -> openArtikelEditor(new ArtikelDTO()));

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRezeptButton);
        toolbar.addClassName("artikel-toolbar");

        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();
        grid.setColumns("name", "einheit", "kategorie");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        artikelDTOS = new ArrayList<>(listArtikelQueryService.execute(new ListArtikelQuery()));
        grid.setItems(artikelDTOS);

        grid.asSingleSelect().addValueChangeListener(event ->
                openArtikelEditor(event.getValue()));
    }

}
