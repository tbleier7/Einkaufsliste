package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.vaadin.flow.component.ComponentEvent;

abstract class ArtikelFormEvent extends ComponentEvent<ArtikelForm> {
    private final ArtikelModel artikelModel;
    public ArtikelFormEvent(ArtikelForm source, ArtikelModel artikelModel) {
        super(source, false);
        this.artikelModel = artikelModel;
    }

    public ArtikelModel getArtikelModel() {
        return artikelModel;
    }
}

