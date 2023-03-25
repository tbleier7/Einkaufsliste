package com.tbleier.kitchenlist.adapter.in.views.einkaufsliste;

import com.tbleier.kitchenlist.application.ports.ZutatDTO;

class SaveListeneintragEvent extends AddArtikelDialogEvent {
    public SaveListeneintragEvent(AddArtikelDialog source, ZutatDTO model) {
        super(source, model);
    }
}
