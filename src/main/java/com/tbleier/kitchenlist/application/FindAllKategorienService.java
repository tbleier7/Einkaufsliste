package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListAllKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;

import java.util.List;

public class FindAllKategorienService implements QueryService<ListAllKategorienQuery, List<Kategorie>> {

    private final KategorieRepository repository;

    public FindAllKategorienService(KategorieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Kategorie> execute(ListAllKategorienQuery query) {
        var kategorien = repository.findAll();
        return kategorien;
    }
}
