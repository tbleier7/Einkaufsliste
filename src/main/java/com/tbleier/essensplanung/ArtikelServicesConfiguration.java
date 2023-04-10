package com.tbleier.essensplanung;

import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel.ArtikelDTOMapper;
import com.tbleier.essensplanung.application.DeleteArtikelService;
import com.tbleier.essensplanung.application.ListArtikelService;
import com.tbleier.essensplanung.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.application.ports.in.CommandService;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.commands.DeleteArtikelCommand;
import com.tbleier.essensplanung.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.essensplanung.application.ports.out.ArtikelRepository;
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
