package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;

public class RemoveZutatService implements CommandService<RemoveZutatCommand> {
    private final EinkaufslisteRepository einkaufslisteRepository;

    public RemoveZutatService(EinkaufslisteRepository einkaufslisteRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
    }

    @Override
    public CommandResult execute(RemoveZutatCommand command) {

        var zutat = einkaufslisteRepository.findByArtikelId(command.getArtikelId());
        einkaufslisteRepository.removeZutat(zutat.get());

        return new CommandResult(true);
    }
}
