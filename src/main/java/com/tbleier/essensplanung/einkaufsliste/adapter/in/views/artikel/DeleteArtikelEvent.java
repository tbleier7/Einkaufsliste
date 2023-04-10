package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel;

import com.tbleier.essensplanung.application.ports.ArtikelDTO;

class DeleteArtikelEvent extends ArtikelFormEvent {
    public DeleteArtikelEvent(ArtikelForm source, ArtikelDTO artikelDTO) {
        super(source, artikelDTO);
    }
}
