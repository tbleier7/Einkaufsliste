package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import java.util.Objects;

public class KategorieModel {

    private String name;

    public KategorieModel() {

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
        KategorieModel kategorie = (KategorieModel) o;
        return name.equals(kategorie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
