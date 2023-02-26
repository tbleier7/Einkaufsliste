package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.adapter.in.views.artikel.ArtikelForm;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.vaadin.flow.component.ComponentEvent;

public abstract class KategorieFormEvent extends ComponentEvent<KategorieForm> {
    private final KategorieModel model;
    public KategorieFormEvent(KategorieForm source, KategorieModel model) {
        super(source, false);
        this.model = model;
    }

    public KategorieModel getModel() {
        return model;
    }
}

