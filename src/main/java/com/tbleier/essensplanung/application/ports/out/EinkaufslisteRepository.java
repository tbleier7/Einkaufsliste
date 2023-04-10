package com.tbleier.essensplanung.application.ports.out;

import com.tbleier.essensplanung.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.application.domain.einkaufsliste.Zutat;

public interface EinkaufslisteRepository {

    long saveZutat(Zutat zutat);

    void save(Einkaufsliste einkaufsliste);

    void removeZutat(Zutat zutat);

    Einkaufsliste getEinkaufsliste();
}
