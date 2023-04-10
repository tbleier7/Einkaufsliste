package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.checkliste;

import com.tbleier.essensplanung.application.ports.ZutatDTO;
import com.vaadin.flow.component.ComponentEvent;

abstract class AddToEinkaufslisteDialogEvent extends ComponentEvent<AddArtikelDialog> {
    private final ZutatDTO model;
    public AddToEinkaufslisteDialogEvent(AddArtikelDialog source, ZutatDTO model) {
        super(source, false);
        this.model = model;
    }

    public ZutatDTO getModel() {
        return model;
    }
}