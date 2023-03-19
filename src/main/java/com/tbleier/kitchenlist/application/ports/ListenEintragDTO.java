package com.tbleier.kitchenlist.application.ports;

public class ListenEintragDTO {

    private String artikelName;
    private int menge;
    private String kategorieName;

    public ListenEintragDTO(String artikelName, int menge, String kategorieName) {
        this.artikelName = artikelName;
        this.menge = menge;
        this.kategorieName = kategorieName;
    }

    public ListenEintragDTO() {
    }

    public String getArtikelName() {
        return artikelName;
    }

    public void setArtikelName(String artikelName) {
        this.artikelName = artikelName;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public String getKategorieName() {
        return kategorieName;
    }

    public void setKategorieName(String kategorieName) {
        this.kategorieName = kategorieName;
    }
}
