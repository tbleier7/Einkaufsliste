package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.adapter.in.views.kategorie.KategorieModelMapper;
import com.tbleier.kitchenlist.application.domain.Kategorie;

public interface KategorieRepository {
    void save(Kategorie kategorie);
    Kategorie findByName(String kategorieName);
}
