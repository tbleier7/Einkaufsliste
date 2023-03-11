package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;
import java.util.stream.Collectors;

public class ArtikelForm extends FormLayout {
    Binder<ArtikelDTO> binder = new BeanValidationBinder<>(ArtikelDTO.class);
    private final CommandService<SaveArtikelCommand> saveArtikelCommandService;
    private final QueryService<ListAllKategorienQuery, List<KategorieDTO>> listKategorieQueryService;
    TextField name = new TextField("Name");
    ComboBox<Einheit> einheit = new ComboBox<>("Einheit");
    ComboBox<String> kategorie = new ComboBox<>("Kategorie");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button cancel = new Button("Abbrechen");

    private ArtikelDTO artikelDTO;


    public ArtikelForm(ArtikelDTO artikelDTO,
                       CommandService<SaveArtikelCommand> SaveArtikelCommandService,
                       QueryService<ListAllKategorienQuery, List<KategorieDTO>> listKategorieQueryService) {

        this.saveArtikelCommandService = SaveArtikelCommandService;
        this.listKategorieQueryService = listKategorieQueryService;
        this.setWidth("25em");
        this.setArtikelModel(artikelDTO);


        binder.bindInstanceFields(this);

        addClassName("artikel-form");

        var kategorienAusDemService = listKategorieQueryService.execute(new ListAllKategorienQuery());

        einheit.setItems(Einheit.values());
        var kategorieNames = kategorienAusDemService.stream().map(KategorieDTO::getName)
                .collect(Collectors.toList());
        kategorie.setItems(kategorieNames);
        kategorie.setLabel("Kategorien");

        add(name, einheit, kategorie, createButtonLayout());
    }


    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(artikelDTO);
        } catch (Exception e) {
            System.out.println("Validation failed");
        }

        //TODO: überarbeiten, der command passt nicht
//        saveArtikelCommandService.execute(new SaveArtikelCommand(
//                        new Artikel(name.getValue(),
//                                einheit.getValue(),
//                                new Kategorie(0, kategorie.getValue()
//                                )
//                        )
//                )
//        );

        fireEvent(new SaveArtikelEvent(this, artikelDTO));
    }

    public void setArtikelModel(ArtikelDTO artikel) {
        this.artikelDTO = artikel;
        binder.readBean(artikel);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
