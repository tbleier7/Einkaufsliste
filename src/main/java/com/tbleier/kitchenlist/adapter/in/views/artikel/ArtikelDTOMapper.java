package com.tbleier.kitchenlist.adapter.in.views.artikel;

import com.tbleier.kitchenlist.application.domain.Artikel;
import com.tbleier.kitchenlist.application.ports.ArtikelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtikelDTOMapper {
    ArtikelDTOMapper INSTANCE = Mappers.getMapper(ArtikelDTOMapper.class );

    @Mapping(source = "kategorie.name", target = "kategorie")
    ArtikelDTO artikelToModel(Artikel artikel);
    List<ArtikelDTO> artikelToModel(List<Artikel> artikel);
}
