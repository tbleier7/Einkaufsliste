package com.tbleier.kitchenlist.adapter.out.persistence.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.out.KategorieRepository;
import com.tbleier.kitchenlist.application.ports.out.NonUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

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
        try {
            kategorieJpaRepository.save(jpaEntity);
        }
        catch(DataIntegrityViolationException dataIntegrityViolationException) {
            throw new NonUniqueException("Kategorie existiert bereits!");
        }

    }

    @Override
    public Kategorie findByName(String kategorieName) {
        var jpaEntity = kategorieJpaRepository.findByName(kategorieName);
        var domainEntity = mapper.jpaEntityToKategorie(jpaEntity);

        return domainEntity;
    }

    @Override
    public List<Kategorie> findAll() {
        var jpaEntities = kategorieJpaRepository.findAll();
        var kategorien = mapper.jpaEntityToKategorie(jpaEntities);

        return kategorien;
    }
}
