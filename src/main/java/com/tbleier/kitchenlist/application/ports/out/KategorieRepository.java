package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.domain.Kategorie;

import java.util.List;
import java.util.Optional;

public interface KategorieRepository {
    void save(Kategorie kategorie);
    Kategorie findByName(String kategorieName);

    Optional<Kategorie> findById(long id);

    List<Kategorie> findAll();

    void delete(long id);
}
