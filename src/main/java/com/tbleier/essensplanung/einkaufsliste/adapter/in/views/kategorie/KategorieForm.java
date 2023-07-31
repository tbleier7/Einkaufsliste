package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie;

import com.tbleier.essensplanung.einkaufsliste.application.ports.KategorieDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteKategorieCommand;
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
    Binder<KategorieDTO> binder = new BeanValidationBinder<>(KategorieDTO.class);
    private final CommandService<SaveKategorieCommand> addKategorieCommandService;
    private final KategorieModelMapper mapper;
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;
    TextField name = new TextField("Name");

    Button save = new Button("Speichern");
    Button cancel = new Button("Abbrechen");
    Button delete = new Button("Löschen");

    private KategorieDTO kategorieDTO;


    public KategorieForm(KategorieDTO kategorieDTO,
                         CommandService<SaveKategorieCommand> addKategorieCommandService,
                         KategorieModelMapper mapper,
                         CommandService<DeleteKategorieCommand> deleteKategorieCommandService) {

        this.addKategorieCommandService = addKategorieCommandService;
        this.mapper = mapper;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
        this.setWidth("25em");
        addClassName("kategorie-form");

        this.setKategorieModel(kategorieDTO);
        binder.bindInstanceFields(this);

        name.setId("kategorie-name-input-field");
        add(name, createButtonLayout());
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        cancel.addClickListener(event -> closeForm());
        delete.addClickListener(event -> deleteKategorie());

        return new HorizontalLayout(save, delete, cancel);
    }

    private void deleteKategorie() {
        CommandResult result = deleteKategorieCommandService.execute(new DeleteKategorieCommand(kategorieDTO.getId()));

        if(result.isSuccessful())
            fireEvent(new DeleteKategorieEvent(this, kategorieDTO));
    }

    public void setKategorieModel(KategorieDTO kategorieDTO) {
        this.kategorieDTO = kategorieDTO;
        binder.readBean(this.kategorieDTO);
    }

    private void closeForm() {
        fireEvent(new CloseEvent(this));
    }

    private void validateAndSave() {
        try {
            binder.writeBean(kategorieDTO);
            var commandResult = addKategorieCommandService
                    .execute(new SaveKategorieCommand(kategorieDTO.getId(), kategorieDTO.getName()));

            if(commandResult.isSuccessful())
                kategorieDTO.setId(commandResult.getId());
                fireEvent(new SaveKategorieEvent(this, kategorieDTO));
        }
        catch (Exception e) {
            System.out.println("Validation failed");
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
