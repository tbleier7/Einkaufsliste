package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.artikel;

import com.tbleier.essensplanung.application.domain.Artikel;
import com.tbleier.essensplanung.application.ports.out.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArtikelPersistenceAdapter implements ArtikelRepository {

    private final ArtikelJpaRepository artikelJpaRepository;
    private final ArtikelJpaMapper artikelJpaMapper;

    @Autowired
    public ArtikelPersistenceAdapter(ArtikelJpaRepository artikelJpaRepository,
                                     ArtikelJpaMapper artikelJpaMapper) {
        this.artikelJpaRepository = artikelJpaRepository;
        this.artikelJpaMapper = artikelJpaMapper;
    }

    @Override
    public long save(Artikel artikel) {
        var artikelJpaEntity = artikelJpaMapper.artikelToJpaEntity(artikel);
        artikelJpaEntity = artikelJpaRepository.save(artikelJpaEntity);
        return artikelJpaEntity.getId();
    }

    @Override
    public Artikel findByName(String artikelName) {
        ArtikelJpaEntity artikelJpaEntity = artikelJpaRepository.findByName(artikelName);
        var mappedArtikel = artikelJpaMapper.jpaEntityToArtikel(artikelJpaEntity);
        return mappedArtikel;
    }

    @Override
    public List<Artikel> findAll() {
        return artikelJpaMapper.jpaEntityToArtikel(artikelJpaRepository.findAll());
    }

    @Override
    public Optional<Artikel> findById(long id) {
        var optional = artikelJpaRepository.findById(id);

        if(optional.isPresent())
            return Optional.of(artikelJpaMapper.jpaEntityToArtikel(optional.get()));
        else
            return Optional.empty();
    }

    @Override
    public void delete(long id) {
        artikelJpaRepository.deleteById(id);
    }
}
