package com.tbleier.essensplanung.adapter.in.views.checkliste;

import com.tbleier.essensplanung.application.ports.ZutatDTO;

class AddZutatEvent extends AddToEinkaufslisteDialogEvent {
    public AddZutatEvent(AddArtikelDialog source, ZutatDTO model) {
        super(source, model);
    }
}
