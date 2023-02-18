package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Zutat;

public interface ZutatPersistenceAdapter {
    void save(Zutat zutat);
}
