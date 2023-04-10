package com.tbleier.essensplanung.einkaufsliste.application.domain;

import java.util.Objects;

public class Artikel {

    private long id;
    private String name;
    private Einheit einheit;
    private Kategorie kategorie;

    public Artikel(long id, String name, Einheit einheit, Kategorie kategorie) {
        this.id = id;
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }

    public String getName() {
        return name;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public long getId() {
        return id;
    }

    public void kategorizeWith(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return id == artikel.id && name.equals(artikel.name) && einheit == artikel.einheit && Objects.equals(kategorie, artikel.kategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, einheit, kategorie);
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", einheit=" + einheit +
                ", kategorie=" + kategorie +
                '}';
    }

    public void rename(String name) {
        this.name = name;
    }

    public void changeEinheitTo(Einheit einheit) {
        this.einheit = einheit;
    }
}
