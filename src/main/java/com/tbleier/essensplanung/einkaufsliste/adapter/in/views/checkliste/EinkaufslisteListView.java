package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.checkliste;

import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.MainLayout;
import com.tbleier.essensplanung.application.ports.ZutatDTO;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.commands.DecrementZutatCommand;
import com.tbleier.essensplanung.application.ports.in.commands.IncrementZutatCommand;
import com.tbleier.essensplanung.application.ports.in.commands.MoveZutatCommand;
import com.tbleier.essensplanung.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.essensplanung.application.ports.in.queries.ListZutatenQuery;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.dnd.GridDropLocation;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
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
    private final CommandService<MoveZutatCommand> moveZutatCommandService;
    private final CommandService<IncrementZutatCommand> incrementZutatCommandService;
    private final CommandService<DecrementZutatCommand> decrementZutatCommandCommandService;
    Grid<ZutatDTO> grid = new Grid<>(ZutatDTO.class, false);

    private List<ZutatDTO> zutatenDTOs = new ArrayList<>();
    Button addArtikelButton;

    ZutatDTO draggedItem;

    @Autowired
    public EinkaufslisteListView(QueryService<ListZutatenQuery, List<ZutatDTO>> einkaufsListeQueryService,
                                 AddArtikelDialogFactory addArtikelDialogFactory,
                                 CommandService<RemoveZutatCommand> removeZutatCommandService,
                                 CommandService<MoveZutatCommand> moveZutatCommandService,
                                 CommandService<IncrementZutatCommand> incrementZutatCommandService,
                                 CommandService<DecrementZutatCommand> decrementZutatCommandCommandService) {
        this.einkaufsListeQueryService = einkaufsListeQueryService;
        this.addArtikelDialogFactory = addArtikelDialogFactory;
        this.removeZutatCommandService = removeZutatCommandService;
        this.moveZutatCommandService = moveZutatCommandService;
        this.incrementZutatCommandService = incrementZutatCommandService;
        this.decrementZutatCommandCommandService = decrementZutatCommandCommandService;

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

        configureCheckBoxColumn();

        grid.addColumn(ZutatDTO::getArtikelName).setHeader("Artikel");

        var mengeColumn = grid.addColumn(ZutatDTO::getMenge).setHeader("Menge");
        mengeColumn.setTextAlign(ColumnTextAlign.CENTER);

        grid.addComponentColumn(item -> {
            var decrementAmountButton = new Button("-");
            decrementAmountButton.addClickListener(event -> {
                decrementZutat(item);
            });
            return decrementAmountButton;
        });


        grid.addComponentColumn(item -> {
            var incrementAmountButton = new Button("+");
            incrementAmountButton.addClickListener(event -> {
                incrementZutat(item);
            });
            return incrementAmountButton;
        });


        grid.setRowsDraggable(true);
        grid.addDragStartListener(
                event -> {
                    // store current dragged item so we know what to drop
                    draggedItem = event.getDraggedItems().get(0);
                    grid.setDropMode(GridDropMode.BETWEEN);
                }
        );

        grid.addDragEndListener(
                event -> {
                    draggedItem = null;
                    // Once dragging has ended, disable drop mode so that
                    // it won't look like other dragged items can be dropped
                    grid.setDropMode(null);
                }
        );

        grid.addDropListener(
                event -> {
                    ZutatDTO dropOverItem = event.getDropTargetItem().get();
                    if (!dropOverItem.equals(draggedItem)) {
                        // reorder dragged item the backing gridItems container
                        zutatenDTOs.remove(draggedItem);
                        // calculate drop index based on the dropOverItem
                        int dropIndex = zutatenDTOs.indexOf(dropOverItem) + (event.getDropLocation() == GridDropLocation.BELOW ? 1 : 0);
                        zutatenDTOs.add(dropIndex, draggedItem);
                        moveZutatCommandService.execute(new MoveZutatCommand(draggedItem.getId(), dropIndex));
                        grid.getDataProvider().refreshAll();
                    }
                }
        );

        refreshGrid();
    }

    private void configureCheckBoxColumn() {
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
    }

    private void refreshGrid() {
        zutatenDTOs = new ArrayList<>(einkaufsListeQueryService.execute(new ListZutatenQuery()));
        grid.setItems(zutatenDTOs);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void incrementZutat(ZutatDTO zutatDTO) {
        var commandResult = incrementZutatCommandService.execute(new IncrementZutatCommand(zutatDTO.getId()));
        if(!commandResult.isSuccessful())
            return;

        refreshGrid();
    }

    private void decrementZutat(ZutatDTO zutatDTO) {
        var commandResult = decrementZutatCommandCommandService.execute(new DecrementZutatCommand(zutatDTO.getId()));
        if(!commandResult.isSuccessful())
            return;

        refreshGrid();
    }

    public List<ZutatDTO> getZutatDTOs() {
        return zutatenDTOs;
    }

    public void addEinkaufslistenposition(ZutatDTO listenposition) {
        zutatenDTOs.add(listenposition);
        grid.setItems(zutatenDTOs);
    }

    public void removeZutat(ZutatDTO zutatDTO) {
        var result = removeZutatCommandService.execute(new RemoveZutatCommand(zutatDTO.getId()));

        if(result.isSuccessful()) {
            zutatenDTOs.remove(zutatDTO);
            grid.setItems(zutatenDTOs);
        }
    }
}

