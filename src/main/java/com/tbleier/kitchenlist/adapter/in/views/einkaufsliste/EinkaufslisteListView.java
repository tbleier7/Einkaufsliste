package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.adapter.in.views.MainLayout;
import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListZutatenQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Einkaufsliste")
@Route(value = "", layout = MainLayout.class)
public class EinkaufslisteListView extends VerticalLayout {

    private final QueryService<ListZutatenQuery, List<ZutatDTO>> einkaufsListeQueryService;
    private final AddArtikelDialogFactory addArtikelDialogFactory;
    private final CommandService<RemoveZutatCommand> removeZutatCommandService;
    Grid<ZutatDTO> grid = new Grid<>(ZutatDTO.class, false);

    private List<ZutatDTO> positionDTOs = new ArrayList<>();
    Button addArtikelButton;


    @Autowired
    public EinkaufslisteListView(QueryService<ListZutatenQuery, List<ZutatDTO>> einkaufsListeQueryService,
                                 AddArtikelDialogFactory addArtikelDialogFactory,
                                 CommandService<RemoveZutatCommand> removeZutatCommandService) {
        this.einkaufsListeQueryService = einkaufsListeQueryService;
        this.addArtikelDialogFactory = addArtikelDialogFactory;
        this.removeZutatCommandService = removeZutatCommandService;

        addClassName("einkaufsliste-list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private Component getToolbar() {

        addArtikelButton = new Button("Eintrag hinzufügen");
        addArtikelButton.addClickListener(event -> openDialog());

        HorizontalLayout toolbar = new HorizontalLayout(addArtikelButton);
        toolbar.addClassName("einkaufsliste-toolbar");

        return toolbar;
    }

    private void openDialog() {

        var dialog = addArtikelDialogFactory.CreateDialog();

        dialog.addListener(AddZutatEvent.class, e -> {
            addEinkaufslistenposition(e.getModel());
        });

        dialog.open();
    }

    private void configureGrid() {
        grid.addClassName("artikel-grid");
        grid.setSizeFull();

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ((GridMultiSelectionModel<?>) grid.getSelectionModel())
                .setSelectAllCheckboxVisibility(
                        GridMultiSelectionModel.SelectAllCheckboxVisibility.HIDDEN
                );

        grid.addSelectionListener(selection -> {
            if(selection.getFirstSelectedItem().isPresent()) {
                removeZutat(selection.getFirstSelectedItem().get());
            }
        });

        grid.addColumn(ZutatDTO::getArtikelName).setHeader("Artikel");

        grid.addComponentColumn(item -> {
            var decrementAmountButton = new Button("-");
            return decrementAmountButton;
        });

        var mengeColumn = grid.addColumn(ZutatDTO::getMenge).setHeader("Menge");
        mengeColumn.setTextAlign(ColumnTextAlign.CENTER);

        grid.addComponentColumn(item -> {
            var incrementAmountButton = new Button("+");
            return incrementAmountButton;
        });

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setRowsDraggable(true);

        positionDTOs = new ArrayList<>(einkaufsListeQueryService.execute(new ListZutatenQuery()));
        grid.setItems(positionDTOs);
    }

    public List<ZutatDTO> getZutatDTOs() {
        return positionDTOs;
    }

    public void addEinkaufslistenposition(ZutatDTO listenposition) {
        positionDTOs.add(listenposition);
        grid.setItems(positionDTOs);
    }

    public void removeZutat(ZutatDTO zutatDTO) {
        var result = removeZutatCommandService.execute(new RemoveZutatCommand(zutatDTO.getId()));

        if(result.isSuccessful()) {
            positionDTOs.remove(zutatDTO);
            grid.setItems(positionDTOs);
        }
    }
}

