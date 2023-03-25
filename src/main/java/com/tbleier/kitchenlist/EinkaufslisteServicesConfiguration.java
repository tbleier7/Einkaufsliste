package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.kitchenlist.application.AddToEinkaufslisteService;
import com.tbleier.kitchenlist.application.ListZutatenService;
import com.tbleier.kitchenlist.application.RemoveZutatService;
import com.tbleier.kitchenlist.application.ZutatenDTOMapper;
import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddToEinkaufsListeCommand;
import com.tbleier.kitchenlist.application.ports.in.commands.RemoveZutatCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListZutatenQuery;
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
}
