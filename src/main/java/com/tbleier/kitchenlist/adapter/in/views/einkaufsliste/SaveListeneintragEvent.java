package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;

public class SaveListeneintragEvent extends AddArtikelDialogEvent {
    public SaveListeneintragEvent(AddArtikelDialog source, EinkaufslistenPositionDTO model) {
        super(source, model);
    }
}
