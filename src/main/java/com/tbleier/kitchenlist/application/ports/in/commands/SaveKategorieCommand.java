package com.tbleier.kitchenlist.application.ports.in.commands;

import com.tbleier.kitchenlist.application.domain.Kategorie;

public class SaveKategorieCommand {
    private final Kategorie kategorie;

    public SaveKategorieCommand(Kategorie kategorie) {

        this.kategorie = kategorie;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }
}
