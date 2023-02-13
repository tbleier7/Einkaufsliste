package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Einheit;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ZutatenForm extends FormLayout {
    TextField rezeptName = new TextField("Name");
    ComboBox<Einheit> einheitComboBox = new ComboBox<>("Einheit");
    ComboBox<Kategorie> kategorieComboBox = new ComboBox<>("Kategorie");

    Button save = new Button("Speichern");
    Button delete = new Button("Löschen");
    Button cancel = new Button("Abbrechen");


    public ZutatenForm(List<Kategorie> kategorien) {
        addClassName("zutaten-form");
        einheitComboBox.setItems(Einheit.values());
        kategorieComboBox.setItems(kategorien);
        kategorieComboBox.setItemLabelGenerator(Kategorie::getName);

        add(rezeptName, einheitComboBox, kategorieComboBox, createButtonLayout());
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }
}
