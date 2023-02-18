package com.tbleier.kitchenlist.adapter.in.views;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddZutatCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZutatenFormFactory {

    private final CommandService<AddZutatCommand> addZutatCommandCommandService;

    public ZutatenFormFactory(CommandService<AddZutatCommand> addZutatCommandCommandService) {
        this.addZutatCommandCommandService = addZutatCommandCommandService;
    }

    public ZutatenForm create(Zutat zutat, List<Kategorie> kategorien) {
        return new ZutatenForm(zutat, kategorien, addZutatCommandCommandService);
    }
}
