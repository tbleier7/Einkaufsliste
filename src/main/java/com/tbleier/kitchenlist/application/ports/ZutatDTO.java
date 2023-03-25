package com.tbleier.kitchenlist.application.ports;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatDTO zutatDTO = (ZutatDTO) o;
        return menge == zutatDTO.menge && artikelName.equals(zutatDTO.artikelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelName, menge);
    }

    @Override
    public String toString() {
        return "ZutatDTO{" +
                "artikelName='" + artikelName + '\'' +
                ", menge=" + menge +
                '}';
    }

    public void setArtikelName(String artikelName) {
        this.artikelName = artikelName;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
