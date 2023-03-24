package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.ZutatDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListZutatenQuery;
import com.tbleier.kitchenlist.application.ports.out.EinkaufslisteRepository;

import java.util.Collections;
import java.util.List;

public class ListZutatenService implements QueryService<ListZutatenQuery, List<ZutatDTO>> {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final ZutatenDTOMapper zutatenDTOMapper;

    public ListZutatenService(EinkaufslisteRepository einkaufslisteRepository,
                              ZutatenDTOMapper zutatenDTOMapper) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.zutatenDTOMapper = zutatenDTOMapper;
    }

    @Override
    public List<ZutatDTO> execute(ListZutatenQuery query) {

        var zutaten = einkaufslisteRepository.listZutaten();
        return zutatenDTOMapper.zutatToDTO(zutaten);
    }
}
