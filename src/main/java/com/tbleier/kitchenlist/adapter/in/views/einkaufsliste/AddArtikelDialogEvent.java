package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.vaadin.flow.component.ComponentEvent;

public abstract class AddArtikelDialogEvent extends ComponentEvent<AddArtikelDialog> {
    private final EinkaufslistenPositionDTO model;
    public AddArtikelDialogEvent(AddArtikelDialog source, EinkaufslistenPositionDTO model) {
        super(source, false);
        this.model = model;
    }

    public EinkaufslistenPositionDTO getModel() {
        return model;
    }
}