package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.domain.einkaufsliste.Zutat;
import com.tbleier.essensplanung.application.ports.ZutatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZutatenDTOMapper {
    ZutatenDTOMapper INSTANCE = Mappers.getMapper(ZutatenDTOMapper.class );

    @Mapping(source = "artikel.name", target = "artikelName")
    @Mapping(source = "artikel.id", target = "artikelId")
    ZutatDTO zutatToDTO(Zutat zutat);
    List<ZutatDTO> zutatToDTO(List<Zutat> zutaten);
}
