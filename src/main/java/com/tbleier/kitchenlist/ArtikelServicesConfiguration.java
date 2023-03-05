package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.kitchenlist.application.ListArtikelService;
import com.tbleier.kitchenlist.application.SaveKategorieService;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArtikelServicesConfiguration {

    private final ArtikelRepository artikelRepository;

    @Autowired
    public ArtikelServicesConfiguration(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    @Bean
    public QueryService<ListArtikelQuery, List<Artikel>> resolveListArtikelQueryService() {
        return new ListArtikelService(artikelRepository);
    }
}
