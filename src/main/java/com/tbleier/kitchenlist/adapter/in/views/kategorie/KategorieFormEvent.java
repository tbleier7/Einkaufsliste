package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.vaadin.flow.component.ComponentEvent;

public abstract class KategorieFormEvent extends ComponentEvent<KategorieForm> {
    private final KategorieDTO model;
    public KategorieFormEvent(KategorieForm source, KategorieDTO model) {
        super(source, false);
        this.model = model;
    }

    public KategorieDTO getModel() {
        return model;
    }
}

