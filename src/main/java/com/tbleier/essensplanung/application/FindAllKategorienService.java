package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.KategorieDTO;
import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.queries.ListKategorienQuery;
import com.tbleier.essensplanung.application.ports.out.KategorieRepository;
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
