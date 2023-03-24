package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Zutat;

import java.util.List;
import java.util.Optional;

public interface EinkaufslisteRepository {
    long save(Zutat zutat);

    List<Zutat> listZutaten();

    Optional<Zutat> findByArtikelId(long artikelId);
}
