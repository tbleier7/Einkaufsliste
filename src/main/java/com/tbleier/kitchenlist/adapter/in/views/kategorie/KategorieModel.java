package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class KategorieModel {
    private long id;

    @NotEmpty
    private String name = "";

    public KategorieModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public KategorieModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        KategorieModel that = (KategorieModel) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "KategorieModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
