package com.tbleier.essensplanung.einkaufsliste.application.ports.out;

import com.tbleier.essensplanung.einkaufsliste.application.domain.Artikel;

import java.util.List;
import java.util.Optional;

public interface ArtikelRepository {
    long save(Artikel artikel);

    Artikel findByName(String artikelName);

    List<Artikel> findAll();

    Optional<Artikel> findById(long id);

    void delete(long id);
}
