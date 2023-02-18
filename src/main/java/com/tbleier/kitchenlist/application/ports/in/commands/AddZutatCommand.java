package com.tbleier.kitchenlist.application.ports.in.commands;

import com.tbleier.kitchenlist.application.domain.Zutat;

public class AddZutatCommand {

    private final Zutat zutat;

    public AddZutatCommand(Zutat zutat) {
        this.zutat = zutat;
    }

    public Zutat getZutat() {
        return zutat;
    }
}
