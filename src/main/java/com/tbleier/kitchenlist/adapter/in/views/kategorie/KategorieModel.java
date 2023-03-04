package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class KategorieModel {

    @NotEmpty
    private String name = "";

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
