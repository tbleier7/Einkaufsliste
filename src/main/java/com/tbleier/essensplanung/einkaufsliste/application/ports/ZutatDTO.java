package com.tbleier.essensplanung.einkaufsliste.application.ports;

import java.util.Objects;

public class ZutatDTO {
    private long id;

    private long artikelId;
    private String artikelName;
    private int menge;

    public ZutatDTO() {

    }

    public ZutatDTO(long id, long artikelId, String artikelName, int menge) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatDTO zutatDTO = (ZutatDTO) o;
        return id == zutatDTO.id && artikelId == zutatDTO.artikelId && menge == zutatDTO.menge && artikelName.equals(zutatDTO.artikelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artikelId, artikelName, menge);
    }

    @Override
    public String toString() {
        return "ZutatDTO{" +
                "id=" + id +
                ", artikelId=" + artikelId +
                ", artikelName='" + artikelName + '\'' +
                ", menge=" + menge +
                '}';
    }
}
