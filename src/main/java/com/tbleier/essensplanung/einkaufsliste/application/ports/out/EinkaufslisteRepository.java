package com.tbleier.essensplanung.einkaufsliste.application.ports.out;

import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.essensplanung.einkaufsliste.application.domain.einkaufsliste.Zutat;

public interface EinkaufslisteRepository {

    long saveZutat(Zutat zutat);

    void save(Einkaufsliste einkaufsliste);

    void removeZutat(Zutat zutat);

    Einkaufsliste getEinkaufsliste();
}
