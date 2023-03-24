package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.domain.Zutat;
import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZutatenDTOMapper {
    ZutatenDTOMapper INSTANCE = Mappers.getMapper(ZutatenDTOMapper.class );

    ZutatDTO zutatToDTO(Zutat zutat);
    List<ZutatDTO> zutatToDTO(List<Zutat> zutaten);
}
