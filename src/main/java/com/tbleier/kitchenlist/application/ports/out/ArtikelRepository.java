package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Artikel;

import java.util.List;

public interface ArtikelRepository {
    void save(Artikel artikel);

    Artikel findByName(String artikelName);

    List<Artikel> findAll();
}
