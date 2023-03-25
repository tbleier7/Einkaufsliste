package com.tbleier.kitchenlist.application.ports.in.commands;

public class RemoveZutatCommand {

    private final long artikelId;

    public RemoveZutatCommand(long artikelId) {
        this.artikelId = artikelId;
    }

    public long getArtikelId() {
        return artikelId;
    }
}
