package com.tbleier.kitchenlist.application.domain;

import java.util.Objects;

public class Kategorie {

    private String name;

    public Kategorie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kategorie kategorie = (Kategorie) o;
        return name.equals(kategorie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
