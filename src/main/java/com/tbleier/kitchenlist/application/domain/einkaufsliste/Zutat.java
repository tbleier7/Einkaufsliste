package com.tbleier.kitchenlist.application.domain.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class Zutat {

    private long id;
    private final Artikel artikel;
    private int menge;
    private int einkaufslisteIndex;
    private Zutat(long id, Artikel artikel, int menge, int einkaufslistenIndex) {
        this.id = id;
        this.artikel = artikel;
        this.menge = menge;
        this.einkaufslisteIndex = einkaufslistenIndex;
    }

    static Zutat CreateWithId(long id, Artikel artikel, int menge, int einkaufslistenIndex) {
        return new Zutat(id, artikel, menge, einkaufslistenIndex);
    }

    static Zutat CreateWithoutId(Artikel artikel, int menge, int einkaufslistenIndex) {
        return new Zutat(0, artikel, menge, einkaufslistenIndex);
    }

    public int getMenge() {
        return menge;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public long getId() {
        return id;
    }

    public int getEinkaufslisteIndex() {
        return einkaufslisteIndex;
    }

    void moveToIndex(int einkaufslisteIndex) {
        this.einkaufslisteIndex = einkaufslisteIndex;
    }

    void increment() {
        this.menge++;
    }

    public void decrement() {
        this.menge--;
    }
}
