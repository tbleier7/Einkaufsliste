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
    private final KategorieModelMapper mapper;

    @Autowired
    public KategorieFormFactory(CommandService<SaveKategorieCommand> addKategorieCommandService,
                                KategorieModelMapper mapper) {
        this.addKategorieCommandService = addKategorieCommandService;
        this.mapper = mapper;
    }

    public KategorieForm create(KategorieModel kategorieModel) {
        return new KategorieForm(kategorieModel, addKategorieCommandService, mapper);
    }
}
