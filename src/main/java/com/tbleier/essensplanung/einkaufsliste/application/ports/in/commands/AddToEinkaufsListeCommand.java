package com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands;

public class AddToEinkaufsListeCommand {

    private final long artikelId;
    private final int menge;

    public AddToEinkaufsListeCommand(long artikelId, int menge) {
        this.artikelId = artikelId;
        this.menge = menge;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public int getMenge() {
        return menge;
    }
}
