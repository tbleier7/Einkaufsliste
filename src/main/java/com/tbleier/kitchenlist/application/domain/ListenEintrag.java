package com.tbleier.kitchenlist.application.domain;

public class ListenEintrag {
    private final Artikel artikel;
    private int menge;

    public ListenEintrag(Artikel artikel, int menge) {
        this.artikel = artikel;
        this.menge = menge;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public int getMenge() {
        return menge;
    }

    @Override
    public String toString() {
        return "ListenEintrag{" +
                "artikel=" + artikel +
                ", menge=" + menge +
                '}';
    }
}
