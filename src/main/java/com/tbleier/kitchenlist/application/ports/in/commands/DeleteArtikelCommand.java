package com.tbleier.kitchenlist.application.ports.in.commands;

public class DeleteArtikelCommand {

    private final long id;

    public DeleteArtikelCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
