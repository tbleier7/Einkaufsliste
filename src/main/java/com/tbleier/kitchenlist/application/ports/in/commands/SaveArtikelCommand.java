package com.tbleier.kitchenlist.application.ports.in.commands;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class SaveArtikelCommand {

    private final Artikel artikel;

    public SaveArtikelCommand(Artikel artikel) {
        this.artikel = artikel;
    }

    public Artikel getZutat() {
        return artikel;
    }
}
