package com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands;

public class SaveKategorieCommand {
    private final long id;
    private final String name;

    public SaveKategorieCommand(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
