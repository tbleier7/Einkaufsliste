package com.tbleier.kitchenlist;

import com.tbleier.kitchenlist.adapter.in.views.interceptors.CommandExceptionHandler;
import com.tbleier.kitchenlist.application.SaveKategorieService;
import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.SaveKategorieCommand;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {

    private final KategorieRepository kategorieRepository;

    @Autowired
    public BeanConfigurations(KategorieRepository kategorieRepository) {
        this.kategorieRepository = kategorieRepository;
    }

    @Bean
    CommandService<SaveKategorieCommand> getSaveKategorieCommandService() {
        return new CommandExceptionHandler<>(new SaveKategorieService(kategorieRepository));
    }
}
