package com.tbleier.kitchenlist.adapter.in.views.checkliste;

import com.tbleier.kitchenlist.application.ports.ZutatDTO;

class AddZutatEvent extends AddToEinkaufslisteDialogEvent {
    public AddZutatEvent(AddArtikelDialog source, ZutatDTO model) {
        super(source, model);
    }
}
