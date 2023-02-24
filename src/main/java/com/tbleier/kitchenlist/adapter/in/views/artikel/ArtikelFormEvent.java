package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.vaadin.flow.component.ComponentEvent;

public abstract class ArtikelFormEvent extends ComponentEvent<ArtikelForm> {
    private final Artikel artikel;
    public ArtikelFormEvent(ArtikelForm source, Artikel artikel) {
        super(source, false);
        this.artikel = artikel;
    }

    public Artikel getArtikel() {
        return artikel;
    }
}

