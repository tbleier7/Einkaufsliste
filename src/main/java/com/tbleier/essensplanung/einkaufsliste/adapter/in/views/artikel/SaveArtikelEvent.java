package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel;

import com.tbleier.essensplanung.einkaufsliste.application.ports.ArtikelDTO;

class SaveArtikelEvent extends ArtikelFormEvent {
    public SaveArtikelEvent(ArtikelForm source, ArtikelDTO artikel) {
        super(source, artikel);
    }
}
