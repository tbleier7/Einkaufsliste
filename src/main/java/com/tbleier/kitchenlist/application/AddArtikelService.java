package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddArtikelService implements CommandService<AddArtikelCommand> {

    private final ArtikelRepository artikelRepository;

    @Autowired
    public AddArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    @Override
    public CommandResult execute(AddArtikelCommand command) {
        System.out.println(command.getZutat().getName());
        artikelRepository.save(command.getZutat());
        return new CommandResult(true);
    }
}
