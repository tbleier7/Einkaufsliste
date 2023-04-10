package com.tbleier.kitchenlist.adapter.in.views.checkliste;

import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
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
