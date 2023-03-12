package com.tbleier.kitchenlist.application.ports;

public class EinkaufslistenPositionDTO {
    private String artikelName;
    private int amount;

    public EinkaufslistenPositionDTO() {
    }

    public EinkaufslistenPositionDTO(String artikelName, int amount) {
        this.artikelName = artikelName;
        this.amount = amount;
    }

    public String getArtikelName() {
        return artikelName;
    }

    public int getAmount() {
        return amount;
    }
}
