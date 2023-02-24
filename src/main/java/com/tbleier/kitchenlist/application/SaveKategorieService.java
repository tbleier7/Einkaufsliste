package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import org.springframework.stereotype.Service;

@Service
public class SaveKategorieService implements CommandService<SaveKategorieCommand> {
    @Override
    public void execute(SaveKategorieCommand command) {
        System.out.println("Adding Kategorie");
        System.out.println(command.getKategorie());
    }
}
