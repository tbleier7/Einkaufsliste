package com.tbleier.essensplanung.adapter.in.views.artikel;

import com.tbleier.essensplanung.application.ports.ArtikelDTO;

class SaveArtikelEvent extends ArtikelFormEvent {
    public SaveArtikelEvent(ArtikelForm source, ArtikelDTO artikel) {
        super(source, artikel);
    }
}
