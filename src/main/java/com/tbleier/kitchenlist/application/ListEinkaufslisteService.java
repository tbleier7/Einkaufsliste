package com.tbleier.kitchenlist.application;

import com.tbleier.kitchenlist.application.ports.EinkaufslistenPositionDTO;
import com.tbleier.kitchenlist.application.ports.in.QueryService;
import com.tbleier.kitchenlist.application.ports.in.queries.ListEinkaufslisteQuery;

import java.util.List;

public class ListEinkaufslisteService implements QueryService<ListEinkaufslisteQuery, List<EinkaufslistenPositionDTO>> {
    @Override
    public List<EinkaufslistenPositionDTO> execute(ListEinkaufslisteQuery query) {
        return List.of(new EinkaufslistenPositionDTO("Irgendwas", 1), new EinkaufslistenPositionDTO("NochmalWas", 1));
    }
}
