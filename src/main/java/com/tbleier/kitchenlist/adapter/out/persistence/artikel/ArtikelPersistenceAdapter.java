package com.tbleier.kitchenlist.adapter.out.persistence.artikel;

import com.tbleier.kitchenlist.adapter.out.persistence.kategorie.KategorieJpaRepository;
import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtikelPersistenceAdapter implements ArtikelRepository {

    private final ArtikelJpaRepository artikelJpaRepository;
    private final KategorieJpaRepository kategorieJpaRepository;

    @Autowired
    public ArtikelPersistenceAdapter(ArtikelJpaRepository artikelJpaRepository,
                                     KategorieJpaRepository kategorieJpaRepository) {
        this.artikelJpaRepository = artikelJpaRepository;
        this.kategorieJpaRepository = kategorieJpaRepository;
    }

    @Override
    public void save(Artikel artikel) {
        var kategorieJpaEntity = kategorieJpaRepository.findByName(artikel.getKategorie().getName());

        ArtikelJpaEntity artikelJpaEntity = new ArtikelJpaEntity();
        artikelJpaEntity.setEinheit(artikel.getEinheit());
        artikelJpaEntity.setName(artikel.getName());
        artikelJpaEntity.setKategorie(kategorieJpaEntity);


        artikelJpaRepository.save(artikelJpaEntity);
    }

    @Override
    public Artikel findByName(String artikelName) {
        ArtikelJpaEntity artikelJpaEntity = artikelJpaRepository.findByName(artikelName);

        //TODO: überarbeiten
        Kategorie kategorie = new Kategorie(0, artikelJpaEntity.getKategorie().getName());

        return new Artikel(0, artikelJpaEntity.getName(), artikelJpaEntity.getEinheit(), kategorie);
    }

    @Override
    public List<Artikel> findAll() {
        return null;
    }
}
