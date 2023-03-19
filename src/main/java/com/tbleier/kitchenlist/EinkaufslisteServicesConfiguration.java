package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.application.AddToEinkaufslisteService;
import com.tbleier.kitchenlist.application.ListEinkaufslisteService;
import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EinkaufslisteServicesConfiguration {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final ArtikelRepository artikelRepository;

    @Autowired
    public EinkaufslisteServicesConfiguration(EinkaufslisteRepository einkaufslisteRepository, ArtikelRepository artikelRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.artikelRepository = artikelRepository;
    }

    @Bean
    public QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> resolveEinkaufslisteQueryService() {
        return new ListEinkaufslisteService();
    }

    @Bean
    public CommandService<AddToEinkaufsListeCommand> resolveAddToEinkaufslisteCommandService() {
        return new AddToEinkaufslisteService(einkaufslisteRepository, artikelRepository);
    }
}
