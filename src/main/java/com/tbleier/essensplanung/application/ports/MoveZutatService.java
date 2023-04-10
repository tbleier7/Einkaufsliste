package com.tbleier.essensplanung.application.ports;

import com.tbleier.essensplanung.application.ports.in.CommandResult;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.commands.MoveZutatCommand;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;

public class MoveZutatService implements CommandService<MoveZutatCommand> {

    private final EinkaufslisteRepository einkaufslisteRepository;

    public MoveZutatService(EinkaufslisteRepository einkaufslisteRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
    }

    @Override
    public CommandResult execute(MoveZutatCommand command) {
        var einkaufsliste = einkaufslisteRepository.getEinkaufsliste();
        einkaufsliste.moveZutatToIndex(command.getZutatId(), command.getDestinationIndex());

        einkaufslisteRepository.save(einkaufsliste);
        return new CommandResult(true);
    }
}
