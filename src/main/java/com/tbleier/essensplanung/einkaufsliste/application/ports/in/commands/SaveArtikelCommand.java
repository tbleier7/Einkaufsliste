package com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands;

import com.tbleier.essensplanung.einkaufsliste.application.domain.Einheit;

public class SaveArtikelCommand {
    private final long id;
    private final String name;
    private final Einheit einheit;
    private final String kategorie;

    public SaveArtikelCommand(long id, String name, Einheit einheit, String kategorie) {
        this.id = id;
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public String getKategorie() {
        return kategorie;
    }
}
