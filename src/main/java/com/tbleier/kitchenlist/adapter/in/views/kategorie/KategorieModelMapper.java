package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KategorieModelMapper {
    KategorieModelMapper INSTANCE = Mappers.getMapper(KategorieModelMapper.class );

    Kategorie modelToKategorie(KategorieModel kategorieModel);

    List<KategorieModel> kategorieToModel(List<Kategorie> kategorieModel);
}
