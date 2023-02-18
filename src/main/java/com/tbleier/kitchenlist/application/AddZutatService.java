package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.in.CommandService;
import com.tbleier.kitchenlist.application.ports.in.commands.AddZutatCommand;
import com.tbleier.kitchenlist.application.ports.out.ZutatPersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddZutatService implements CommandService<AddZutatCommand> {

    private final ZutatPersistenceAdapter zutatPersistenceAdapter;

    @Autowired
    public AddZutatService(ZutatPersistenceAdapter zutatPersistenceAdapter) {
        this.zutatPersistenceAdapter = zutatPersistenceAdapter;
    }

    @Override
    public void execute(AddZutatCommand command) {
        System.out.println(command.getZutat().getName());
        zutatPersistenceAdapter.save(command.getZutat());
    }
}
