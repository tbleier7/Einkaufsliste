package com.tbleier.essensplanung.einkaufsliste.application.ports;

import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;

public class KategorieDTO {
    private long id;

    @NotEmpty
    private String name = "";

    public KategorieDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public KategorieDTO() {
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
        KategorieDTO that = (KategorieDTO) o;
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
