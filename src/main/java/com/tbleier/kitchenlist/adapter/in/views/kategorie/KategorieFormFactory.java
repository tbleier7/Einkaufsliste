package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.DeleteKategorieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KategorieFormFactory {

    private final CommandService<SaveKategorieCommand> addKategorieCommandService;
    private final KategorieModelMapper mapper;
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;

    @Autowired
    public KategorieFormFactory(CommandService<SaveKategorieCommand> addKategorieCommandService,
                                KategorieModelMapper mapper,
                                CommandService<DeleteKategorieCommand> deleteKategorieCommandService) {
        this.addKategorieCommandService = addKategorieCommandService;
        this.mapper = mapper;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
    }

    public KategorieForm create(KategorieDTO kategorieDTO) {
        return new KategorieForm(kategorieDTO, addKategorieCommandService, mapper, deleteKategorieCommandService);
    }
}
