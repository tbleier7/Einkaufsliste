package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Einkaufslistenposition;

public interface EinkaufslisteRepository {
    void save(Einkaufslistenposition einkaufslistenposition);
}
