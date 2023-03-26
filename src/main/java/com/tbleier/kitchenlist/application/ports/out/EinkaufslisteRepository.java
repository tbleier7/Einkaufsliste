package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;

import java.util.List;
import java.util.Optional;

public interface EinkaufslisteRepository {
    long save(Zutat zutat);

    List<Zutat> listZutaten();

    Optional<Zutat> findByArtikelId(long artikelId);

    void removeZutat(Zutat zutat);

    void save(Einkaufsliste einkaufsliste);

    Einkaufsliste getEinkaufsliste();
}
