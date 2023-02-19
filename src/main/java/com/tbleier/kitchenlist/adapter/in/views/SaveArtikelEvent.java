package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class SaveArtikelEvent extends ArtikelFormEvent {
    public SaveArtikelEvent(ArtikelForm source, Artikel artikel) {
        super(source, artikel);
    }
}
