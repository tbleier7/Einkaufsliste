package com.tbleier.kitchenlist.application.ports;

import com.tbleier.kitchenlist.application.domain.Einheit;
import java.util.Objects;


public class ArtikelDTO {
    private long id;
    private String name;
    private Einheit einheit;
    private String kategorie;

    public ArtikelDTO(long id, String name, Einheit einheit, String kategorie) {
        this.id = id;
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }

    public ArtikelDTO() {
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
        ArtikelDTO that = (ArtikelDTO) o;
        return id == that.id && name.equals(that.name) && einheit == that.einheit && kategorie.equals(that.kategorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, einheit, kategorie);
    }

    @Override
    public String toString() {
        return "ArtikelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", einheit=" + einheit +
                ", kategorie='" + kategorie + '\'' +
                '}';
    }
}

