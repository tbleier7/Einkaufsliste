package com.tbleier.essensplanung.einkaufsliste.application;

import com.tbleier.essensplanung.einkaufsliste.adapter.in.views.artikel.ArtikelDTOMapper;
import com.tbleier.essensplanung.einkaufsliste.application.ports.ArtikelDTO;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.QueryService;
import com.tbleier.essensplanung.einkaufsliste.application.ports.in.queries.ListArtikelQuery;
import com.tbleier.essensplanung.einkaufsliste.application.ports.out.ArtikelRepository;

import java.util.Collections;
import java.util.List;

public class ListArtikelService implements QueryService<ListArtikelQuery, List<ArtikelDTO>> {

    private final ArtikelRepository artikelRepository;
    private final ArtikelDTOMapper artikelDTOMapper;

    public ListArtikelService(ArtikelRepository artikelRepository, ArtikelDTOMapper artikelDTOMapper) {
        this.artikelRepository = artikelRepository;
        this.artikelDTOMapper = artikelDTOMapper;
    }

    @Override
    public List<ArtikelDTO> execute(ListArtikelQuery query) {
        var artikel = artikelRepository.findAll();
        if(artikel == null)
            return Collections.emptyList();
        else
            return artikelDTOMapper.artikelToModel(artikel);
    }
}
