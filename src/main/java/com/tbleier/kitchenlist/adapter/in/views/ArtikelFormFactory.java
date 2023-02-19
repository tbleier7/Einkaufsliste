package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddArtikelCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtikelFormFactory {

    private final CommandService<AddArtikelCommand> addZutatCommandCommandService;

    public ArtikelFormFactory(CommandService<AddArtikelCommand> addZutatCommandCommandService) {
        this.addZutatCommandCommandService = addZutatCommandCommandService;
    }

    public ArtikelForm create(Artikel artikel, List<Kategorie> kategorien) {
        return new ArtikelForm(artikel, kategorien, addZutatCommandCommandService);
    }
}
