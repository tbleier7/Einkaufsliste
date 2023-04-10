package com.tbleier.essensplanung.adapter.in.views.kategorie;

import com.tbleier.essensplanung.application.ports.KategorieDTO;

public class SaveKategorieEvent extends KategorieFormEvent {
    public SaveKategorieEvent(KategorieForm source, KategorieDTO artikel) {
        super(source, artikel);
    }
}
