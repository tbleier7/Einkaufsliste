package com.tbleier.essensplanung.application.domain;

import java.util.Objects;

public class Kategorie {

    private long id;

    private String name;

    public Kategorie(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kategorie kategorie = (Kategorie) o;
        return id == kategorie.id && name.equals(kategorie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
