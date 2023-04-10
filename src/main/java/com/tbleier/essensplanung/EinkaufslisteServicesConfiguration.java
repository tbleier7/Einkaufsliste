package com.tbleier.essensplanung;

import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.essensplanung.application.*;
import com.tbleier.essensplanung.application.ports.MoveZutatService;
import com.tbleier.essensplanung.application.ports.ZutatDTO;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.commands.*;
import com.tbleier.essensplanung.application.ports.in.queries.ListZutatenQuery;
import com.tbleier.essensplanung.application.ports.out.ArtikelRepository;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EinkaufslisteServicesConfiguration {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final ArtikelRepository artikelRepository;
    private final ZutatenDTOMapper zutatenDTOMapper;

    @Autowired
    public EinkaufslisteServicesConfiguration(EinkaufslisteRepository einkaufslisteRepository,
                                              ArtikelRepository artikelRepository,
                                              ZutatenDTOMapper zutatenDTOMapper) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.artikelRepository = artikelRepository;
        this.zutatenDTOMapper = zutatenDTOMapper;
    }

    @Bean
    public QueryService<ListZutatenQuery, List<ZutatDTO>> resolveEinkaufslisteQueryService() {
        return new ListZutatenService(einkaufslisteRepository, zutatenDTOMapper);
    }

    @Bean
    public CommandService<AddToEinkaufsListeCommand> resolveAddToEinkaufslisteCommandService() {
        return new CommandExceptionHandler<>(new AddToEinkaufslisteService(einkaufslisteRepository, artikelRepository));
    }

    @Bean
    public CommandService<RemoveZutatCommand> resolveRemoveZutatCommandService() {
        return new CommandExceptionHandler<>(new RemoveZutatService(einkaufslisteRepository));
    }

    @Bean
    public CommandService<MoveZutatCommand> resolveMoveZutatCommandService() {
        return new CommandExceptionHandler<>(new MoveZutatService(einkaufslisteRepository));
    }

    @Bean
    public CommandService<IncrementZutatCommand> resolveIncrementZutatCommandService() {
        return new CommandExceptionHandler<>(new IncrementZutatService(einkaufslisteRepository));
    }

    @Bean
    public CommandService<DecrementZutatCommand> resolveDecrementZutatCommandService() {
        return new CommandExceptionHandler<>(new DecrementZutatService(einkaufslisteRepository));
    }
}
