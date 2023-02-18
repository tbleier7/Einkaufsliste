package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Zutat;
import com.vaadin.flow.component.ComponentEvent;

public abstract class ZutatenFormEvent extends ComponentEvent<ZutatenForm> {
    private final Zutat zutat;
    public ZutatenFormEvent(ZutatenForm source, Zutat zutat) {
        super(source, false);
        this.zutat = zutat;
    }

    public Zutat getZutat() {
        return zutat;
    }
}

