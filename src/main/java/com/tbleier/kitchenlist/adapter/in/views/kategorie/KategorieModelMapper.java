package com.tbleier.kitchenlist.adapter.in.views.kategorie;

import com.tbleier.kitchenlist.application.domain.Kategorie;
import com.tbleier.kitchenlist.application.ports.KategorieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KategorieModelMapper {
    KategorieModelMapper INSTANCE = Mappers.getMapper(KategorieModelMapper.class );

    Kategorie modelToKategorie(KategorieDTO kategorieDTO);

    List<KategorieDTO> kategorieToModel(List<Kategorie> kategorien);
}
