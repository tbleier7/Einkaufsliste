package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddZutatCommand;
import org.springframework.stereotype.Service;

@Service
public class AddZutatService implements CommandService<AddZutatCommand> {
    @Override
    public void execute(AddZutatCommand command) {
        System.out.println(command.getZutat().getName());
    }
}
