package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Kategorie;

public class DeleteKategorieCommand {

    private final Kategorie kategorie;

    public DeleteKategorieCommand(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Kategorie getKategorie() {
        return this.kategorie;
    }
}
