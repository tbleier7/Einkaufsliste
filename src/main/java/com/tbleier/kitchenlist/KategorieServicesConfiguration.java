package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.DeleteKategorieService;
import com.tbleier.kitchenlist.application.FindAllKategorienService;
import com.tbleier.kitchenlist.application.SaveKategorieService;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KategorieServicesConfiguration {

    private final KategorieRepository kategorieRepository;
    private final KategorieModelMapper kategorieModelMapper;

    @Autowired
    public KategorieServicesConfiguration(KategorieRepository kategorieRepository, KategorieModelMapper kategorieModelMapper) {
        this.kategorieRepository = kategorieRepository;
        this.kategorieModelMapper = kategorieModelMapper;
    }

    @Bean
    CommandService<SaveKategorieCommand> resolveSaveKategorieCommandService() {
        return new CommandExceptionHandler<>(new SaveKategorieService(kategorieRepository));
    }

    @Bean
    CommandService<DeleteKategorieCommand> resolveDeleteKategorieCommandService() {
        return new CommandExceptionHandler<>(new DeleteKategorieService(kategorieRepository));
    }

    @Bean
    QueryService<ListAllKategorienQuery, List<KategorieDTO>> resolveListKategorienQueryService() {
        return new FindAllKategorienService(kategorieRepository, kategorieModelMapper);
    }
}
