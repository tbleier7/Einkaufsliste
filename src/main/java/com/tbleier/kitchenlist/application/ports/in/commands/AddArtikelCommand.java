package com.tbleier.kitchenlist.application.ports.in.commands;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class AddArtikelCommand {

    private final Artikel artikel;

    public AddArtikelCommand(Artikel artikel) {
        this.artikel = artikel;
    }

    public Artikel getZutat() {
        return artikel;
    }
}
