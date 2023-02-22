package com.tbleier.kitchenlist.adapter.out.persistence.artikel;

import javax.persistence.*;

@Entity
@Table(name = "kategorie")
public class KategorieJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "kategorie")
    private ArtikelJpaEntity artikel;

    public ArtikelJpaEntity getArtikel() {
        return artikel;
    }

    public void setArtikel(ArtikelJpaEntity artikel) {
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
