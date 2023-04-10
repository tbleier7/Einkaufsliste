package com.tbleier.essensplanung.einkaufsliste.adapter.in.views.checkliste;

import com.tbleier.essensplanung.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.essensplanung.application.ports.in.queries.ListArtikelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddArtikelDialogFactory {

    private final QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService;
    private final CommandService<AddToEinkaufsListeCommand> addToEinkaufsListeCommandService;

    @Autowired
    public AddArtikelDialogFactory(QueryService<ListArtikelQuery, List<ArtikelDTO>> listArtikelQueryService,
                                   CommandService<AddToEinkaufsListeCommand> addToEinkaufsListeCommandService) {
        this.listArtikelQueryService = listArtikelQueryService;
        this.addToEinkaufsListeCommandService = addToEinkaufsListeCommandService;
    }

    AddArtikelDialog CreateDialog() {
        return new AddArtikelDialog(listArtikelQueryService, addToEinkaufsListeCommandService);
    }
}
