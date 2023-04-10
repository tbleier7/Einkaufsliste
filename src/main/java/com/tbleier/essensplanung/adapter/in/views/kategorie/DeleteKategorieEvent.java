package com.tbleier.essensplanung.adapter.in.views.kategorie;

import com.tbleier.essensplanung.application.ports.KategorieDTO;

class DeleteKategorieEvent extends KategorieFormEvent {
    public DeleteKategorieEvent(KategorieForm source, KategorieDTO model) {
        super(source, model);
    }
}
