package com.depromeet.sulsul.domain.beerFlavor.service;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beerFlavor.dto.BeerFlavorRequest;
import com.depromeet.sulsul.domain.beerFlavor.entity.BeerFlavor;
import com.depromeet.sulsul.domain.beerFlavor.repository.BeerFlavorRepository;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.record.entity.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeerFlavorService {
    private final BeerFlavorRepository beerFlavorRepository;
    private final BeerRepository beerRepository;
    private final FlavorRepository flavorRepository;

    @Transactional
    public void save(BeerFlavorRequest beerFlavorRequest, Record record){
        List<BeerFlavor> beerFlavorList = new ArrayList<>();
        Beer beer = beerRepository.getById(beerFlavorRequest.getBeerId());
        for(int i=0; i<beerFlavorRequest.getFlavorIds().size(); i++){
            beerFlavorList.add(new BeerFlavor(
                        beer
                        , flavorRepository.getById(beerFlavorRequest.getFlavorIds().get(i))
                        , record
                    )
            );
        }
        beerFlavorRepository.saveAll(beerFlavorList);
    }

}
