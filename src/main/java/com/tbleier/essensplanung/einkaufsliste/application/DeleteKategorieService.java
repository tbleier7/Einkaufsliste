package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteKategorieCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.KategorieRepository;

public class DeleteKategorieService implements CommandService<DeleteKategorieCommand> {

    private final KategorieRepository kategorieRepository;

    public DeleteKategorieService(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public CommandResult execute(DeleteKategorieCommand command) {

        kategorieRepository.delete(command.getId());
        return new CommandResult(true);
    }
}
