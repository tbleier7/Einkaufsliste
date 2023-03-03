package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;

public class DeleteKategorieService implements CommandService<DeleteKategorieCommand> {

    private final KategorieRepository kategorieRepository;

    public DeleteKategorieService(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public void execute(DeleteKategorieCommand command) {
        kategorieRepository.delete(command.getKategorie());
    }
}
