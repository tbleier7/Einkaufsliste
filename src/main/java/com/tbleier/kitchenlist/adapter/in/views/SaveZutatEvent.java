package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Zutat;

public class SaveZutatEvent extends ZutatenFormEvent {
    public SaveZutatEvent(ZutatenForm source, Zutat zutat) {
        super(source, zutat);
    }
}
