package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandResult;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.CommandService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.EinkaufslisteRepository;

public class AddToEinkaufslisteService implements CommandService<AddToEinkaufsListeCommand> {

    private final EinkaufslisteRepository repository;
    private final ArtikelRepository artikelRepository;

    public AddToEinkaufslisteService(EinkaufslisteRepository repository, ArtikelRepository artikelRepository) {
        this.repository = repository;
        this.artikelRepository = artikelRepository;
    }

    @Override
    public CommandResult execute(AddToEinkaufsListeCommand command) {
        var artikel = artikelRepository.findById(command.getArtikelId());

        if(artikel.isEmpty())
            return new CommandResult(false);

        var einkaufsliste = repository.getEinkaufsliste();
        var zutat = einkaufsliste.addZutat(artikel.get(), command.getMenge());
        var zutatId = repository.saveZutat(zutat);

        return new CommandResult(true, zutatId);
    }
}
