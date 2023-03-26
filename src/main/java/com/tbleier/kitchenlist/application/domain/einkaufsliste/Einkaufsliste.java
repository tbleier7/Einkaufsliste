package com.tbleier.kitchenlist.application.domain.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Artikel;

import java.util.LinkedList;
import java.util.List;

public class Einkaufsliste {

    private List<Zutat> zutaten = new LinkedList<>();

    Einkaufsliste(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public Einkaufsliste() {
    }

    public void addZutat(Artikel artikel, int menge) {
        var highestIndex = zutaten.size();

        var newZutat = new Zutat(artikel, menge, highestIndex);
        zutaten.add(newZutat);
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void moveZutatToIndex(long artikelId, int index) {
        var zutatToMove= zutaten.stream().filter(zutat -> zutat.getArtikel().getId() == artikelId ).findFirst();
        zutaten.remove(zutatToMove.get());

        zutaten.add(index, zutatToMove.get());
    }

    public static Einkaufsliste CreateWithZutaten(List<Zutat> zutaten) {

        zutaten = new LinkedList<>(zutaten);
        return new Einkaufsliste(zutaten);
    }
}
