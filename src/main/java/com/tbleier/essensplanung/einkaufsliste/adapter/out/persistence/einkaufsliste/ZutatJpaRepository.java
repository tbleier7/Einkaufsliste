package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.einkaufsliste;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZutatJpaRepository extends JpaRepository<ZutatJpaEntity, Long> {
    Optional<ZutatJpaEntity> findByArtikelId(long artikelId);
    List<ZutatJpaEntity> findAllByOrderByEinkaufslisteIndexAsc(); // don't miss "by"
}
