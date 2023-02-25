package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.domain.Kategorie;

import java.util.List;

public interface KategorieRepository {
    void save(Kategorie kategorie);
    Kategorie findByName(String kategorieName);

    List<Kategorie> findAll();
}
