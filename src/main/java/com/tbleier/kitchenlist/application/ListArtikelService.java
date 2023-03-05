package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListArtikelQuery;

import java.util.Collections;
import java.util.List;

public class ListArtikelService implements QueryService<ListArtikelQuery, List<Artikel>> {

    @Override
    public List<Artikel> execute(ListArtikelQuery query) {
        return Collections.emptyList();
    }
}
