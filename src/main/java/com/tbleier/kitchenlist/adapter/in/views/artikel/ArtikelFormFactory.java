package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtikelFormFactory {

    private final CommandService<SaveArtikelCommand> addZutatCommandCommandService;

    public ArtikelFormFactory(CommandService<SaveArtikelCommand> addZutatCommandCommandService) {
        this.addZutatCommandCommandService = addZutatCommandCommandService;
    }

    public ArtikelForm create(ArtikelModel artikelModel, List<String> kategorien) {
        return new ArtikelForm(artikelModel, kategorien, addZutatCommandCommandService);
    }
}
