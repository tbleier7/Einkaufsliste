package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;

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
