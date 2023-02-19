package com.tbleier.kitchenlist.adapter.out;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.stereotype.Component;

import java.io.Console;

@Component
public class ArtikelPersistenceAdapter implements ArtikelRepository {
    @Override
    public void save(Artikel artikel) {
        System.out.println("Save " + artikel.toString());
    }
}
