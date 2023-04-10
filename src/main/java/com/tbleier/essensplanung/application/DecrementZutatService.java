package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.in.CommandResult;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.commands.DecrementZutatCommand;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;

public class DecrementZutatService implements CommandService<DecrementZutatCommand> {

    private final EinkaufslisteRepository einkaufslisteRepository;

    public DecrementZutatService(EinkaufslisteRepository einkaufslisteRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
    }

    @Override
    public CommandResult execute(DecrementZutatCommand command) {
        var einkaufsliste = einkaufslisteRepository.getEinkaufsliste();
        var decrementedZutat = einkaufsliste.decrementZutat(command.getZutatId());
        einkaufslisteRepository.saveZutat(decrementedZutat);

        return new CommandResult(true, decrementedZutat.getId());
    }
}
