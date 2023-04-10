package com.tbleier.essensplanung.application;

import com.tbleier.essensplanung.application.ports.ZutatDTO;
import com.tbleier.essensplanung.application.ports.in.QueryService;
import com.tbleier.essensplanung.application.ports.in.queries.ListZutatenQuery;
import com.tbleier.essensplanung.application.ports.out.EinkaufslisteRepository;

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

        var einkaufsliste = einkaufslisteRepository.getEinkaufsliste();
        var zutaten = einkaufsliste.getZutaten();

        return zutatenDTOMapper.zutatToDTO(zutaten);
    }
}
