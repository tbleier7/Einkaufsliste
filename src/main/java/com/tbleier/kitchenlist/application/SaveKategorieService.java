package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveKategorieService implements CommandService<SaveKategorieCommand> {

    private final KategorieRepository kategorieRepository;

    @Autowired
    public SaveKategorieService(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public void execute(SaveKategorieCommand command) {
        kategorieRepository.save(command.getKategorie());
    }
}
