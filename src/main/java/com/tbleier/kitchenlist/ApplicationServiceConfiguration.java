package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.kitchenlist.application.DeleteKategorieService;
import com.tbleier.kitchenlist.application.SaveKategorieService;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.DeleteKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfiguration {

    private final KategorieRepository kategorieRepository;

    @Autowired
    public ApplicationServiceConfiguration(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Bean
    CommandService<SaveKategorieCommand> resolveSaveKategorieCommandService() {
        return new CommandExceptionHandler<>(new SaveKategorieService(kategorieRepository));
    }

    @Bean
    CommandService<DeleteKategorieCommand> resolveDeleteKategorieCommandService() {
        return new CommandExceptionHandler<>(new DeleteKategorieService(kategorieRepository));
    }
}
