package com.tbleier.kitchenlist.adapter.out.persistence.artikel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelJpaRepository extends JpaRepository<ArtikelJpaEntity, Long> {
    ArtikelJpaEntity findByName(String name);
}
