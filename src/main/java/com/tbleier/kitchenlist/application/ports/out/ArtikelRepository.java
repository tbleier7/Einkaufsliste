package com.tbleier.kitchenlist.application.ports.out;

import com.tbleier.kitchenlist.application.domain.Artikel;

public interface ArtikelRepository {
    void save(Artikel artikel);
}
