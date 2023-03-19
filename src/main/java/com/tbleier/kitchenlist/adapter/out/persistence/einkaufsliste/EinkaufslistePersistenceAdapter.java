package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Einkaufslistenposition;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.springframework.stereotype.Component;

@Component
public class EinkaufslistePersistenceAdapter implements EinkaufslisteRepository {
    @Override
    public void save(Einkaufslistenposition einkaufslistenposition) {

    }
}
