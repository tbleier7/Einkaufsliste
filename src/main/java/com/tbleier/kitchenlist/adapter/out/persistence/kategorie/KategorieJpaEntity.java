package com.tbleier.kitchenlist.adapter.out.persistence.kategorie;

import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kategorie")
public class KategorieJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public KategorieJpaEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public KategorieJpaEntity() {
    }

    private String name;

    @OneToMany(mappedBy = "kategorie")
    private Set<ArtikelJpaEntity> artikel = new HashSet<>();


    public Set<ArtikelJpaEntity> getArtikel() {
        return artikel;
    }

    public void setArtikel(Set<ArtikelJpaEntity> artikel) {
        this.artikel = artikel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
