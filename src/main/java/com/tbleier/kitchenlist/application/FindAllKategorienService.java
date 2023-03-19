package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListKategorienQuery;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class FindAllKategorienService implements QueryService<ListKategorienQuery, List<KategorieDTO>> {


    private final KategorieRepository repository;
    private final KategorieModelMapper mapper;

    @Autowired
    public FindAllKategorienService(KategorieRepository repository, KategorieModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<KategorieDTO> execute(ListKategorienQuery query) {
        var kategorien = repository.findAll();

        if(kategorien == null)
            return Collections.emptyList();

        return mapper.kategorieToModel(kategorien);
    }
}
