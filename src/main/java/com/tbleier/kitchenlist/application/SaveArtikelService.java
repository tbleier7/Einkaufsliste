package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveArtikelService implements CommandService<SaveArtikelCommand> {

    private final ArtikelRepository artikelRepository;

    @Autowired
    public SaveArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    @Override
    public CommandResult execute(SaveArtikelCommand command) {
        System.out.println(command.getArtikel().getName());
        artikelRepository.save(command.getArtikel());
        return new CommandResult(true);
    }
}
