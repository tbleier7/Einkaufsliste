package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie;

import com.tbleier.essensplanung.application.domain.Kategorie;
import com.tbleier.essensplanung.application.ports.out.KategorieRepository;
import com.tbleier.essensplanung.application.ports.out.NonUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public long save(Kategorie kategorie) {
        var jpaEntity = mapper.kategorieToJpaEntity(kategorie);

        try {
            jpaEntity = kategorieJpaRepository.save(jpaEntity);
            return jpaEntity.getId();
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
    public Optional<Kategorie> findById(long id) {
         var kategorieJpaEntityOptional = kategorieJpaRepository.findById(id);

         if(kategorieJpaEntityOptional.isEmpty())
             return Optional.empty();
         else
             return Optional.of(mapper.jpaEntityToKategorie(kategorieJpaEntityOptional.get()));
    }

    @Override
    public List<Kategorie> findAll() {
        var jpaEntities = kategorieJpaRepository.findAll();
        var kategorien = mapper.jpaEntityToKategorie(jpaEntities);

        return kategorien;
    }

    @Override
    public void delete(long id) {
        var jpaEntity = kategorieJpaRepository.findById(id);

        if(!jpaEntity.isEmpty())
            kategorieJpaRepository.delete(jpaEntity.get());
    }
}
