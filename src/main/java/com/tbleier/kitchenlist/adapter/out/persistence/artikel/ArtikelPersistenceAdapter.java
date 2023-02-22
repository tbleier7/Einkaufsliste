package com.tbleier.kitchenlist.adapter.out.persistence.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtikelPersistenceAdapter implements ArtikelRepository {

    private final ArtikelJpaRepository artikelJpaRepository;

    @Autowired
    public ArtikelPersistenceAdapter(ArtikelJpaRepository artikelJpaRepository) {
        this.artikelJpaRepository = artikelJpaRepository;
    }

    @Override
    public void save(Artikel artikel) {
        KategorieJpaEntity kategorieJpaEntity = new KategorieJpaEntity();
        kategorieJpaEntity.setName(artikel.getKategorie().getName());

        ArtikelJpaEntity artikelJpaEntity = new ArtikelJpaEntity();
        artikelJpaEntity.setEinheit(artikel.getEinheit());
        artikelJpaEntity.setName(artikel.getName());

        artikelJpaEntity.setKategorie(kategorieJpaEntity);


        artikelJpaRepository.save(artikelJpaEntity);
    }

    @Override
    public Artikel findByName(String artikelName) {
        ArtikelJpaEntity artikelJpaEntity = artikelJpaRepository.findByName(artikelName);

        Kategorie kategorie = new Kategorie(artikelJpaEntity.getKategorie().getName());

        return new Artikel(artikelJpaEntity.getName(), artikelJpaEntity.getEinheit(), kategorie);
    }
}
