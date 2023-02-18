package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Zutat;

public class DeleteZutatEvent extends ZutatenFormEvent {
    public DeleteZutatEvent(ZutatenForm source, Zutat zutat) {
        super(source, zutat);
    }
}
