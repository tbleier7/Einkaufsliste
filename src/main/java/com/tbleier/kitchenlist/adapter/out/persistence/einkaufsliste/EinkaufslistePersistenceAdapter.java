package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.ListenEintrag;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.springframework.stereotype.Component;

@Component
public class EinkaufslistePersistenceAdapter implements EinkaufslisteRepository {
    @Override
    public void saveListenEintrag(ListenEintrag listenEintrag) {

    }
}
