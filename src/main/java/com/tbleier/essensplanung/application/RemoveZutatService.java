package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.in.CommandResult;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;

public class RemoveZutatService implements CommandService<RemoveZutatCommand> {
    private final EinkaufslisteRepository einkaufslisteRepository;

    public RemoveZutatService(EinkaufslisteRepository einkaufslisteRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
    }

    @Override
    public CommandResult execute(RemoveZutatCommand command) {

        var einkaufsliste = einkaufslisteRepository.getEinkaufsliste();

        var zutat = einkaufsliste.getZutat(command.getZutatId());
        if(zutat.isPresent())
            einkaufslisteRepository.removeZutat(zutat.get());

        return new CommandResult(true);
    }
}
