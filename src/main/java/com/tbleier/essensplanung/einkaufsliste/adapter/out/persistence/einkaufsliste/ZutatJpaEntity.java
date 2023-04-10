package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.einkaufsliste;

import com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.artikel.ArtikelJpaEntity;

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

    private int einkaufslisteIndex;

    public ZutatJpaEntity(ArtikelJpaEntity artikel, int menge, int einkaufslisteIndex) {
        this.artikel = artikel;
        this.menge = menge;
        this.einkaufslisteIndex = einkaufslisteIndex;
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

    public int getEinkaufslisteIndex() {
        return einkaufslisteIndex;
    }

    public void setEinkaufslisteIndex(int einkaufslisteIndex) {
        this.einkaufslisteIndex = einkaufslisteIndex;
    }
}
