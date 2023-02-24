package com.tbleier.kitchenlist.adapter.out.persistence.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KategoriePersistenceAdapter implements KategorieRepository {

    private final KategorieJpaRepository kategorieJpaRepository;
    private final KategorieJpaMapper mapper;

    @Autowired
    public KategoriePersistenceAdapter(KategorieJpaRepository kategorieJpaRepository, KategorieJpaMapper mapper) {
        this.kategorieJpaRepository = kategorieJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Kategorie kategorie) {
        var jpaEntity = mapper.kategorieToJpaEntity(kategorie);
        kategorieJpaRepository.save(jpaEntity);
    }

    @Override
    public Kategorie findByName(String kategorieName) {
        var jpaEntity = kategorieJpaRepository.findByName(kategorieName);
        var domainEntity = mapper.jpaEntityToKategorie(jpaEntity);

        return domainEntity;
    }
}
