package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.application.ListEinkaufslisteService;
import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EinkaufslisteServicesConfiguration {

    public EinkaufslisteServicesConfiguration() {
    }

    @Bean
    public QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> resolveEinkaufslisteQueryService() {
        return new ListEinkaufslisteService();
    }
}
