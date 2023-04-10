package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.in.CommandResult;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.essensplanung.application.ports.out.ArtikelRepository;

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
