package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class KategorieForm extends FormLayout {
    Binder<KategorieModel> binder = new BeanValidationBinder<>(KategorieModel.class);
    private final CommandService<SaveKategorieCommand> addKategorieCommandService;
    private final KategorieModelMapper mapper;
    TextField name = new TextField("Name");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button cancel = new Button("Abbrechen");

    private KategorieModel kategorie;


    public KategorieForm(KategorieModel kategorieModel,
                         CommandService<SaveKategorieCommand> addKategorieCommandService,
                         KategorieModelMapper mapper) {

        this.addKategorieCommandService = addKategorieCommandService;
        this.mapper = mapper;
        this.setWidth("25em");
        this.setKategorie(kategorieModel);


        binder.bindInstanceFields(this);
        addClassName("kategorie-form");

        add(name, createButtonLayout());
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
            binder.writeBean(kategorie);
        }
        catch (Exception e) {
            System.out.println("Validation failed");
        }

        var kategorie = mapper.modelToKategorie(this.kategorie);
        addKategorieCommandService.execute(new SaveKategorieCommand(kategorie));
    }

    public void setKategorie(KategorieModel kategorie) {
        this.kategorie = kategorie;
        binder.readBean(kategorie);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
