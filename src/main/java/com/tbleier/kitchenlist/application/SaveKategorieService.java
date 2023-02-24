package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveKategorieService implements CommandService<SaveKategorieCommand> {


    private final KategorieRepository kategorieRepository;

    @Autowired
    public SaveKategorieService(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public void execute(SaveKategorieCommand command) {
        System.out.println("Adding Kategorie");
        System.out.println(command.getKategorie());

        kategorieRepository.save(command.getKategorie());
    }
}
