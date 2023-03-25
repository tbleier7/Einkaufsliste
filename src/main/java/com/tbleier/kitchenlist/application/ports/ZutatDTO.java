package com.tbleier.kitchenlist.application.ports;

import java.util.Objects;

public class ZutatDTO {
    private long artikelId;

    private String artikelName;
    private int menge;

    public ZutatDTO() {

    }

    public ZutatDTO(long artikelId, String artikelName, int menge) {
        this.artikelId = artikelId;
        this.artikelName = artikelName;
        this.menge = menge;
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

    public long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(long artikelId) {
        this.artikelId = artikelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatDTO zutatDTO = (ZutatDTO) o;
        return artikelId == zutatDTO.artikelId && menge == zutatDTO.menge && artikelName.equals(zutatDTO.artikelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelId, artikelName, menge);
    }

    @Override
    public String toString() {
        return "ZutatDTO{" +
                "artikelId=" + artikelId +
                ", artikelName='" + artikelName + '\'' +
                ", menge=" + menge +
                '}';
    }
}
