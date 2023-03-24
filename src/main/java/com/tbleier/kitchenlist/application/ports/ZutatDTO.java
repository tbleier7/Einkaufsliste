package com.tbleier.kitchenlist.application.ports;

public class ZutatDTO {
    private String artikelName;
    private int menge;

    public ZutatDTO() {
    }

    public ZutatDTO(String artikelName, int menge) {
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
