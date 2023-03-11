package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.vaadin.flow.component.ComponentEvent;

abstract class ArtikelFormEvent extends ComponentEvent<ArtikelForm> {
    private final ArtikelDTO artikelDTO;
    public ArtikelFormEvent(ArtikelForm source, ArtikelDTO artikelDTO) {
        super(source, false);
        this.artikelDTO = artikelDTO;
    }

    public ArtikelDTO getArtikelModel() {
        return artikelDTO;
    }
}

