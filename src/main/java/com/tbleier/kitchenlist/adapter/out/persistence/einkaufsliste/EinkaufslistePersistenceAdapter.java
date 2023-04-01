package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaMapper;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Einkaufsliste;
import com.tbleier.kitchenlist.application.domain.einkaufsliste.Zutat;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EinkaufslistePersistenceAdapter implements EinkaufslisteRepository {

    private final ZutatJpaRepository zutatJpaRepository;
    private final ZutatJpaMapper zutatJpaMapper;
    private final ArtikelJpaMapper artikelJpaMapper;

    @Autowired
    public EinkaufslistePersistenceAdapter(ZutatJpaRepository zutatJpaRepository, ZutatJpaMapper zutatJpaMapper, ArtikelJpaMapper artikelJpaMapper) {
        this.zutatJpaRepository = zutatJpaRepository;
        this.zutatJpaMapper = zutatJpaMapper;
        this.artikelJpaMapper = artikelJpaMapper;
    }

    @Override
    public long save(Zutat zutat) {

        var jpaEntity = zutatJpaMapper.zutatToJpaEntity(zutat);
        jpaEntity = zutatJpaRepository.save(jpaEntity);

        return jpaEntity.getId();
    }

    @Override
    @Transactional
    public void removeZutat(Zutat zutat) {
        zutatJpaRepository.deleteById(zutat.getId());
    }

    @Override
    public Einkaufsliste getEinkaufsliste() {
        var jpaEntities = zutatJpaRepository.findAll();

        var einkaufsListe = Einkaufsliste.CreateEmpty();
        for (var jpaZutat: jpaEntities) {
            einkaufsListe.addExistingZutat(jpaZutat.getId(), artikelJpaMapper.jpaEntityToArtikel(jpaZutat.getArtikel()), jpaZutat.getMenge());
        }

        return einkaufsListe;
    }
}
