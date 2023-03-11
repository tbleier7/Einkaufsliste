package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.artikel.ArtikelDTOMapper;
import com.tbleier.kitchenlist.application.DeleteArtikelService;
import com.tbleier.kitchenlist.application.ListArtikelService;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArtikelServicesConfiguration {

    private final ArtikelRepository artikelRepository;
    private final ArtikelDTOMapper artikelDTOMapper;

    @Autowired
    public ArtikelServicesConfiguration(ArtikelRepository artikelRepository, ArtikelDTOMapper artikelDTOMapper) {
        this.artikelRepository = artikelRepository;
        this.artikelDTOMapper = artikelDTOMapper;
    }

    @Bean
    public QueryService<ListArtikelQuery, List<ArtikelDTO>> resolveListArtikelQueryService() {
        return new ListArtikelService(artikelRepository, artikelDTOMapper);
    }

    @Bean
    public CommandService<DeleteArtikelCommand> resolveDeleteArtikelCommandService() {
        return new DeleteArtikelService(artikelRepository);
    }
}
