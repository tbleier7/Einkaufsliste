package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import org.springframework.stereotype.Component;

@Component
public class KategorieFormFactory {

    private final CommandService<SaveKategorieCommand> addKategorieCommandService;

    public KategorieFormFactory(CommandService<SaveKategorieCommand> addKategorieCommandService) {
        this.addKategorieCommandService = addKategorieCommandService;
    }

    public KategorieForm create(Kategorie kategorie) {
        return new KategorieForm(kategorie, addKategorieCommandService);
    }
}
