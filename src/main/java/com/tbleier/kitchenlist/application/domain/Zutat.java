package com.tbleier.kitchenlist.application.domain;

import java.util.Objects;

public class Zutat {

    public void setName(String name) {
        this.name = name;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    private String name;
    private Einheit einheit;
    private Kategorie kategorie;

    public Zutat(String name, Einheit einheit, Kategorie kategorie) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zutat zutat = (Zutat) o;
        return name.equals(zutat.name) && einheit == zutat.einheit && kategorie.equals(zutat.kategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, einheit, kategorie);
    }

    @Override
    public String toString() {
        return "Zutat{" +
                "name='" + name + '\'' +
                ", einheit=" + einheit +
                ", kategorie=" + kategorie +
                '}';
    }
}
