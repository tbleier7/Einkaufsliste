package com.tbleier.essensplanung.einkaufsliste.adapter.out.persistence.kategorie;

import com.tbleier.essensplanung.einkaufsliste.application.domain.Kategorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KategorieJpaMapper {
    KategorieJpaMapper INSTANCE = Mappers.getMapper(KategorieJpaMapper.class );

    KategorieJpaEntity kategorieToJpaEntity(Kategorie kategorie);

    Kategorie jpaEntityToKategorie(KategorieJpaEntity jpaEntity);

    List<Kategorie> jpaEntityToKategorie(List<KategorieJpaEntity> jpaEntity);
}
