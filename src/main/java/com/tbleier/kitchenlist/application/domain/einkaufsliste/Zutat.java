package com.tbleier.kitchenlist.application.domain.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class Zutat {

    private long id;

    private final Artikel artikel;
    private int menge;
    private int einkaufslistenIndex;
    private Zutat(long id, Artikel artikel, int menge, int einkaufslistenIndex) {
        this.id = id;
        this.artikel = artikel;
        this.menge = menge;
        this.einkaufslistenIndex = einkaufslistenIndex;
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

    public int getEinkaufslistenIndex() {
        return einkaufslistenIndex;
    }

    void moveToIndex(int einkaufslistenIndex) {
        this.einkaufslistenIndex = einkaufslistenIndex;
    }
}
