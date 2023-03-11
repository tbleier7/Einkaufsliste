package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;

class SaveArtikelEvent extends ArtikelFormEvent {
    public SaveArtikelEvent(ArtikelForm source, ArtikelDTO artikel) {
        super(source, artikel);
    }
}
