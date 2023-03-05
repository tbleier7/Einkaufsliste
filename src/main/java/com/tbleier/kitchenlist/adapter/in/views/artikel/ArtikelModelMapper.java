package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtikelModelMapper {
    ArtikelModelMapper INSTANCE = Mappers.getMapper(ArtikelModelMapper.class );

    @Mapping(source = "kategorie.name", target = "kategorie")
    ArtikelModel artikelToModel(Artikel artikel);
    List<ArtikelModel> artikelToModel(List<Artikel> artikel);
}
