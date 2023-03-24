package com.tbleier.kitchenlist.adapter.out.persistence.einkaufsliste;

import com.tbleier.kitchenlist.adapter.out.persistence.artikel.ArtikelJpaEntity;

import javax.persistence.*;

@Entity
@Table(name = "zutat")
public class ZutatJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "artikel_id", referencedColumnName = "id")
    private ArtikelJpaEntity artikel;
    private int menge;

    public ZutatJpaEntity(ArtikelJpaEntity artikel, int menge) {
        this.artikel = artikel;
        this.menge = menge;
    }

    public ZutatJpaEntity() {
    }

    public ArtikelJpaEntity getArtikel() {
        return artikel;
    }

    public void setArtikel(ArtikelJpaEntity artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
