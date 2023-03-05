package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;

class DeleteArtikelEvent extends ArtikelFormEvent {
    public DeleteArtikelEvent(ArtikelForm source, ArtikelModel artikelModel) {
        super(source, artikelModel);
    }
}
