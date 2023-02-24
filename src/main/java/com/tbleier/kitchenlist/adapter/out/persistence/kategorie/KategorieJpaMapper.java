package com.tbleier.kitchenlist.adapter.out.persistence.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KategorieJpaMapper {
    KategorieJpaMapper INSTANCE = Mappers.getMapper(KategorieJpaMapper.class );

    KategorieJpaEntity kategorieToJpaEntity(Kategorie kategorie);
    Kategorie jpaEntityToKategorie(KategorieJpaEntity jpaEntity);
}
