package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class EinkaufslistePersistenceAdapter implements EinkaufslisteRepository {

    private final ZutatJpaRepository zutatJpaRepository;
    private final ZutatJpaMapper zutatJpaMapper;

    @Autowired
    public EinkaufslistePersistenceAdapter(ZutatJpaRepository zutatJpaRepository, ZutatJpaMapper zutatJpaMapper) {
        this.zutatJpaRepository = zutatJpaRepository;
        this.zutatJpaMapper = zutatJpaMapper;
    }

    @Override
    public long save(Zutat zutat) {

        var jpaEntity = zutatJpaMapper.zutatToJpaEntity(zutat);

        jpaEntity = zutatJpaRepository.save(jpaEntity);

        return jpaEntity.getId();
    }

    @Override
    public List<Zutat> listZutaten() {

        var jpaEntities = zutatJpaRepository.findAll();
        var zutaten = zutatJpaMapper.jpaEntityToZutat(jpaEntities);

        return zutaten;
    }

    @Override
    public Optional<Zutat> findByArtikelId(long artikelId) {

        var jpaEntity = zutatJpaRepository.findByArtikelId(artikelId);
        if(jpaEntity.isEmpty())
            return Optional.empty();
        else
            return Optional.of(zutatJpaMapper.jpaEntityToZutat(jpaEntity.get()));
    }

    @Override
    @Transactional
    public void removeZutat(Zutat zutat) {
        zutatJpaRepository.deleteByArtikelId(zutat.getArtikel().getId());
    }
}
