package com.tbleier.essensplanung.application.ports.in.commands;

public class DeleteKategorieCommand {

    private final long id;

    public DeleteKategorieCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
