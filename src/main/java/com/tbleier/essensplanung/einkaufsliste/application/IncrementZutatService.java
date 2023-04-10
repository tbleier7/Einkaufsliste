package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.IncrementZutatCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.EinkaufslisteRepository;

public class IncrementZutatService implements CommandService<IncrementZutatCommand> {
    private final EinkaufslisteRepository einkaufslisteRepository;

    public IncrementZutatService(EinkaufslisteRepository einkaufslisteRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
    }


    @Override
    public CommandResult execute(IncrementZutatCommand command) {
        var einkaufsliste = einkaufslisteRepository.getEinkaufsliste();
        var incrementedZutat = einkaufsliste.incrementZutat(command.getZutatId());
        einkaufslisteRepository.saveZutat(incrementedZutat);

        return new CommandResult(true, incrementedZutat.getId());
    }
}
