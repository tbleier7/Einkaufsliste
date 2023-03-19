package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.ListenEintragDTO;
import com.vaadin.flow.component.ComponentEvent;

public abstract class AddArtikelDialogEvent extends ComponentEvent<AddArtikelDialog> {
    private final ListenEintragDTO model;
    public AddArtikelDialogEvent(AddArtikelDialog source, ListenEintragDTO model) {
        super(source, false);
        this.model = model;
    }

    public ListenEintragDTO getModel() {
        return model;
    }
}