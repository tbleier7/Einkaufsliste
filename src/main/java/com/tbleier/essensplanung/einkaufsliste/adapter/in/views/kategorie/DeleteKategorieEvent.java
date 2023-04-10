package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie;

import com.tbleier.essensplanung.einkaufsliste.application.ports.KategorieDTO;

class DeleteKategorieEvent extends KategorieFormEvent {
    public DeleteKategorieEvent(KategorieForm source, KategorieDTO model) {
        super(source, model);
    }
}
