package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
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
import java.util.StringTokenizer;

public class ArtikelForm extends FormLayout {
    Binder<ArtikelModel> binder = new BeanValidationBinder<>(ArtikelModel.class);
    private final CommandService<SaveArtikelCommand> addZutatCommandCommandService;
    TextField name = new TextField("Name");
    ComboBox<Einheit> einheit = new ComboBox<>("Einheit");
    ComboBox<String> kategorie = new ComboBox<>("Kategorie");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button cancel = new Button("Abbrechen");

    private ArtikelModel artikel;


    public ArtikelForm(ArtikelModel artikel, List<String> kategorien, CommandService<SaveArtikelCommand> addZutatCommandCommandService) {

        this.addZutatCommandCommandService = addZutatCommandCommandService;
        this.setWidth("25em");
        this.setArtikelModel(artikel);


        binder.bindInstanceFields(this);

        addClassName("artikel-form");
        einheit.setItems(Einheit.values());
        kategorie.setItems(kategorien);
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
            binder.writeBean(artikel);
        } catch (Exception e) {
            System.out.println("Validation failed");
        }

        addZutatCommandCommandService.execute(new SaveArtikelCommand(
                        new Artikel(name.getValue(),
                                einheit.getValue(),
                                new Kategorie(kategorie.getValue()
                                )
                        )
                )
        );
    }

    public void setArtikelModel(ArtikelModel artikel) {
        this.artikel = artikel;
        binder.readBean(artikel);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
