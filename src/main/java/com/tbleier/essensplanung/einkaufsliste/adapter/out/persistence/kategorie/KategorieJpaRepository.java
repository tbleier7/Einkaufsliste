package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorieJpaRepository extends JpaRepository<KategorieJpaEntity, Long> {
    KategorieJpaEntity findByName(String kategorieName);
}
