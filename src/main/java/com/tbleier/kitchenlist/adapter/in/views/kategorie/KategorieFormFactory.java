package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KategorieFormFactory {

    private final CommandService<SaveKategorieCommand> addKategorieCommandService;
    private final CommandService<DeleteKategorieCommand> deleteKategorieCommandService;
    private final KategorieModelMapper mapper;

    @Autowired
    public KategorieFormFactory(CommandService<SaveKategorieCommand> addKategorieCommandService,
                                CommandService<DeleteKategorieCommand> deleteKategorieCommandService,
                                KategorieModelMapper mapper) {
        this.addKategorieCommandService = addKategorieCommandService;
        this.deleteKategorieCommandService = deleteKategorieCommandService;
        this.mapper = mapper;
    }

    public KategorieForm create(KategorieModel kategorieModel) {
        return new KategorieForm(kategorieModel, addKategorieCommandService,deleteKategorieCommandService, mapper);
    }
}
