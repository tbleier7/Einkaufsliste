package com.tbleier.kitchenlist.application.domain;

public class Zutat {

    private final Artikel artikel;
    private int menge;

    public Zutat(Artikel artikel, int menge) {
        this.artikel = artikel;
        this.menge = menge;
    }

    public int getMenge() {
        return menge;
    }

    public Artikel getArtikel() {
        return artikel;
    }
}
