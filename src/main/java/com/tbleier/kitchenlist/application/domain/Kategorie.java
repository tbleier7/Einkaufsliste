package com.tbleier.kitchenlist.application.domain;

public class Kategorie {

    private final String name;

    public Kategorie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Kategorie{" +
                "name='" + name + '\'' +
                '}';
    }
}
