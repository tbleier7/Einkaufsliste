package com.tbleier.kitchenlist.adapter.out.persistence.artikel;
import com.tbleier.kitchenlist.adapter.out.persistence.kategorie.KategorieJpaEntity;
import com.tbleier.kitchenlist.application.domain.Einheit;

import javax.persistence.*;

@Entity
@Table(name = "artikel")
public class ArtikelJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public ArtikelJpaEntity(Long id, String name, Einheit einheit, KategorieJpaEntity kategorie) {
        this.id = id;
        this.name = name;
        this.einheit = einheit;
        setKategorie(kategorie);
    }

    public ArtikelJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private Einheit einheit;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "kategorie_id", referencedColumnName = "id")
    private KategorieJpaEntity kategorie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    public KategorieJpaEntity getKategorie() {
        return kategorie;
    }

    public void setKategorie(KategorieJpaEntity kategorie) {
        kategorie.getArtikel().add(this);
        this.kategorie = kategorie;
    }
}
