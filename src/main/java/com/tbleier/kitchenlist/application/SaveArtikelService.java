package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.adapter.in.views.artikel.ArtikelDTOMapper;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandResult;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveArtikelCommand;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveArtikelService implements CommandService<SaveArtikelCommand> {

    private final ArtikelRepository artikelRepository;
    private final KategorieRepository kategorieRepository;

    @Autowired
    public SaveArtikelService(ArtikelRepository artikelRepository,
                              KategorieRepository kategorieRepository) {
        this.artikelRepository = artikelRepository;
        this.kategorieRepository = kategorieRepository;
    }

    @Override
    public CommandResult execute(SaveArtikelCommand command) {
        Artikel artikel;

        var artikelOptional = artikelRepository.findById(command.getId());
        var kategorie = kategorieRepository.findByName(command.getKategorie());

        if(artikelOptional.isEmpty()) {
            artikel = new Artikel(0, command.getName(), command.getEinheit(), kategorie);
        }
        else {
            artikel = artikelOptional.get();
            artikel.kategorizeWith(kategorie);
            artikel.rename(command.getName());
            artikel.changeEinheitTo(command.getEinheit());
        }

        var id = artikelRepository.save(artikel);
        return new CommandResult(true, id);
    }
}
