package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class AddArtikelDialog extends Dialog {

    private final QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;
    private final CommandService<AddToEinkaufsListeCommand> addToEinkaufsListeCommandService;
    Button cancelButton = new Button("Abbrechen");
    Button addButton = new Button("Hinzufügen");

    private List<ArtikelDTO> artikelDTOs = new ArrayList<>();

    ComboBox<ArtikelDTO> artikelDTOComboBox;
    IntegerField mengenIntegerField;

    @Autowired
    public AddArtikelDialog(QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService,
                            CommandService<AddToEinkaufsListeCommand> addToEinkaufsListeCommandService) {
        this.listArtikelQueryService = listArtikelQueryService;
        this.addToEinkaufsListeCommandService = addToEinkaufsListeCommandService;

        configureComboBox();
        mengenIntegerField = new IntegerField();
        mengenIntegerField.setLabel("Menge");
        mengenIntegerField.addValueChangeListener(event -> enableButton());
        mengenIntegerField.setValue(1);

        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(artikelDTOComboBox, mengenIntegerField);

        addButton.addClickListener(event -> addSelectedToEinkaufsliste());
        enableButton();

        cancelButton.addClickListener(event -> this.close());
        add(horizontalLayout, addButton, cancelButton);

        initialize();
    }

    private void configureComboBox() {
        artikelDTOComboBox = new ComboBox<>("Artikel");
        artikelDTOComboBox.setItemLabelGenerator(ArtikelDTO::getName);
        artikelDTOComboBox.addValueChangeListener(event -> {
            enableButton();
        });
    }

    private void enableButton() {
        boolean isEnabled = artikelDTOComboBox.getOptionalValue().isPresent() && mengenIntegerField.getOptionalValue().isPresent() && mengenIntegerField.getValue() > 0;
        addButton.setEnabled(isEnabled);
    }

    private void addSelectedToEinkaufsliste() {
        var result = addToEinkaufsListeCommandService
                .execute(new AddToEinkaufsListeCommand(artikelDTOComboBox.getValue().getId(), mengenIntegerField.getValue()));

        if(result.isSuccessful()) {
            fireEvent(new AddZutatEvent(this, new ZutatDTO(result.getId(), artikelDTOComboBox.getValue().getId(), artikelDTOComboBox.getValue().getName(),
                    mengenIntegerField.getValue()
            )));

            this.close();
        }
    }

    public List<ArtikelDTO> getArtikelDTOs() {
        return artikelDTOs;
    }

    private void initialize() {
        artikelDTOs = listArtikelQueryService.execute(new ListArtikelQuery());
        artikelDTOComboBox.setItems(artikelDTOs);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
