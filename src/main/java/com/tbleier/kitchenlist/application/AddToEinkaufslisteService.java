package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import com.tbleier.kitchenlist.application.ports.out.NonUniqueException;

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
        var zutatId = repository.save(zutat);

        return new CommandResult(true, zutatId);
    }
}
