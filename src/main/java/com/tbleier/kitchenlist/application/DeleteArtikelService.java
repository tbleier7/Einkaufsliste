package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;

public class DeleteArtikelService implements CommandService<DeleteArtikelCommand> {

    private final ArtikelRepository artikelRepository;

    public DeleteArtikelService(ArtikelRepository artikelRepository) {

        this.artikelRepository = artikelRepository;
    }

    @Override
    public CommandResult execute(DeleteArtikelCommand command) {
        artikelRepository.delete(command.getId());

        return new CommandResult(true, 0L);
    }
}
