package com.tbleier.kitchenlist.application.ports;

public class EinkaufslistenPositionDTO {
    private String artikelName;
    private int menge;

    public EinkaufslistenPositionDTO() {
    }

    public EinkaufslistenPositionDTO(String artikelName, int menge) {
        this.artikelName = artikelName;
        this.menge = menge;
    }

    public String getArtikelName() {
        return artikelName;
    }

    public int getMenge() {
        return menge;
    }
}
