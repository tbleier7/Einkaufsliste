package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;

class SaveArtikelEvent extends ArtikelFormEvent {
    public SaveArtikelEvent(ArtikelForm source, Artikel artikel) {
        super(source, artikel);
    }
}
