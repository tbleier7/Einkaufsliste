package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtikelFormFactory {

    private final CommandService<SaveArtikelCommand> addZutatCommandCommandService;
    private final QueryService<ListAllKategorienQuery, List<KategorieDTO>> listKategorieQueryService;

    public ArtikelFormFactory(CommandService<SaveArtikelCommand> addZutatCommandCommandService,
                              QueryService<ListAllKategorienQuery, List<KategorieDTO>> listKategorieQueryService) {
        this.addZutatCommandCommandService = addZutatCommandCommandService;
        this.listKategorieQueryService = listKategorieQueryService;
    }

    public ArtikelForm create(ArtikelDTO artikelDTO) {
        return new ArtikelForm(artikelDTO, addZutatCommandCommandService, listKategorieQueryService);
    }
}
