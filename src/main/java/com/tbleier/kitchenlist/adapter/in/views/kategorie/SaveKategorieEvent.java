package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;

public class SaveKategorieEvent extends KategorieFormEvent {
    public SaveKategorieEvent(KategorieForm source, KategorieDTO artikel) {
        super(source, artikel);
    }
}
