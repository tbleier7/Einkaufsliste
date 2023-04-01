package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;

public interface EinkaufslisteRepository {

    long save(Zutat zutat);

    void removeZutat(Zutat zutat);

    Einkaufsliste getEinkaufsliste();
}
