package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZutatJpaRepository extends JpaRepository<ZutatJpaEntity, Long> {
    Optional<ZutatJpaEntity> findByArtikelId(long artikelId);
    void deleteByArtikelId(long artikelId);
}
