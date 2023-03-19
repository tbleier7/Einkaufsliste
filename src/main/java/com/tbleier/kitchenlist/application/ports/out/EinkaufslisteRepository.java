package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.ListenEintrag;

public interface EinkaufslisteRepository {
    void saveListenEintrag(ListenEintrag listenEintrag);
}
