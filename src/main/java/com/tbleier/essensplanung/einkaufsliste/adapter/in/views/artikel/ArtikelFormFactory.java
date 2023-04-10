package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel;

import com.tbleier.essensplanung.einkaufsliste.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.KategorieDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.QueryService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.queries.ListKategorienQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtikelFormFactory {

    private final CommandService<SaveArtikelCommand> addZutatCommandCommandService;
    private final QueryService<ListKategorienQuery, List<KategorieDTO>> listKategorieQueryService;
    private final CommandService<DeleteArtikelCommand> deleteArtikelCommandService;

    public ArtikelFormFactory(CommandService<SaveArtikelCommand> addZutatCommandCommandService,
                              QueryService<ListKategorienQuery, List<KategorieDTO>> listKategorieQueryService,
                              CommandService<DeleteArtikelCommand> deleteArtikelCommandService) {
        this.addZutatCommandCommandService = addZutatCommandCommandService;
        this.listKategorieQueryService = listKategorieQueryService;
        this.deleteArtikelCommandService = deleteArtikelCommandService;
    }

    public ArtikelForm create(ArtikelDTO artikelDTO) {
        return new ArtikelForm(artikelDTO, addZutatCommandCommandService, listKategorieQueryService, deleteArtikelCommandService);
    }
}
