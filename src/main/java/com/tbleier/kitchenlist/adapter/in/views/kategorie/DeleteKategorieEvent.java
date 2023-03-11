package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;

class DeleteKategorieEvent extends KategorieFormEvent {
    public DeleteKategorieEvent(KategorieForm source, KategorieDTO model) {
        super(source, model);
    }
}
