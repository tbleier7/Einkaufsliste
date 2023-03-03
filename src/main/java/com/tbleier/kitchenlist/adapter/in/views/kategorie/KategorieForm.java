package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
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
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;
    private final KategorieModelMapper mapper;
    TextField name = new TextField("Name");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button cancel = new Button("Abbrechen");

    private KategorieModel kategorieModel;


    public KategorieForm(KategorieModel kategorieModel,
                         CommandService<SaveKategorieCommand> addKategorieCommandService,
                         CommandService<DeleteKategorieCommand> deleteKategorieCommandService,
                         KategorieModelMapper mapper) {

        this.addKategorieCommandService = addKategorieCommandService;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
        this.mapper = mapper;
        this.setWidth("25em");
        addClassName("kategorie-form");

        this.setKategorieModel(kategorieModel);
        binder.bindInstanceFields(this);


        add(name, createButtonLayout());
    }



    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> deleteKategorie());
        cancel.addClickListener(event -> closeForm());

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setKategorieModel(KategorieModel kategorieModel) {
        this.kategorieModel = kategorieModel;
        binder.readBean(this.kategorieModel);
    }

    private void closeForm() {
        fireEvent(new CloseEvent(this));
    }

    private void deleteKategorie() {
        var kategorie = mapModelToKategorie();
        deleteKategorieCommandService.execute(new DeleteKategorieCommand(kategorie));
        fireEvent(new DeleteKategorieEvent(this, kategorieModel));
    }

    private void validateAndSave() {
        try {
            binder.writeBean(kategorieModel);
        }
        catch (Exception e) {
            System.out.println("Validation failed");
        }

        var kategorie = mapModelToKategorie();
        addKategorieCommandService.execute(new SaveKategorieCommand(kategorie));
        fireEvent(new SaveKategorieEvent(this, kategorieModel));
    }

    private Kategorie mapModelToKategorie() {
        return mapper.modelToKategorie(kategorieModel);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
