package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.vaadin.flow.component.ComponentEvent;

abstract class AddArtikelDialogEvent extends ComponentEvent<AddArtikelDialog> {
    private final ZutatDTO model;
    public AddArtikelDialogEvent(AddArtikelDialog source, ZutatDTO model) {
        super(source, false);
        this.model = model;
    }

    public ZutatDTO getModel() {
        return model;
    }
}