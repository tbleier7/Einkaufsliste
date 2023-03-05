package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Einheit;
import java.util.Objects;


public class ArtikelModel {
    private String name;
    private Einheit einheit;
    private String kategorie;

    public ArtikelModel(String name, Einheit einheit, String kategorie) {
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }

    public ArtikelModel() {
        this.name = "";
        this.einheit = null;
        this.kategorie = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtikelModel that = (ArtikelModel) o;
        return Objects.equals(name, that.name) && einheit == that.einheit && Objects.equals(kategorie, that.kategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, einheit, kategorie);
    }

    @Override
    public String toString() {
        return "ArtikelModel{" +
                "name='" + name + '\'' +
                ", einheit=" + einheit +
                ", kategorie='" + kategorie + '\'' +
                '}';
    }
}
