package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;

class DeleteArtikelEvent extends ArtikelFormEvent {
    public DeleteArtikelEvent(ArtikelForm source, ArtikelDTO artikelDTO) {
        super(source, artikelDTO);
    }
}
