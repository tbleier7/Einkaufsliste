package com.tbleier.kitchenlist.application.domain.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Artikel;

public class Zutat {

    private final Artikel artikel;
    private int menge;
    private int einkaufslistenIndex;

    public Zutat(Artikel artikel, int menge, int einkaufslistenIndex) {
        this.artikel = artikel;
        this.menge = menge;
        this.einkaufslistenIndex = einkaufslistenIndex;
    }

    public int getMenge() {
        return menge;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public int getEinkaufslistenIndex() {
        return einkaufslistenIndex;
    }

    void moveToIndex(int einkaufslistenIndex) {
        this.einkaufslistenIndex = einkaufslistenIndex;
    }
}
