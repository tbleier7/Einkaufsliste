package com.tbleier.kitchenlist.application.domain;

public class Zutat {

    private final String name;
    private final Einheit einheit;
    private final Kategorie kategorie;

    public Zutat(String name, Einheit einheit, Kategorie kategorie) {
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }
    public String getName() {
        return name;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

}
