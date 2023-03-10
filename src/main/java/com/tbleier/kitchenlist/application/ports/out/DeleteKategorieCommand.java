package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Kategorie;

public class DeleteKategorieCommand {

    private final long id;

    public DeleteKategorieCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
