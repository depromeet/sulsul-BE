package com.depromeet.sulsul.domain.recordsFlavor.service;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.records.entity.Records;
import com.depromeet.sulsul.domain.recordsFlavor.dto.RecordsFlavorRequest;
import com.depromeet.sulsul.domain.recordsFlavor.entity.RecordsFlavor;
import com.depromeet.sulsul.domain.recordsFlavor.repository.RecordsFlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class RecordsFlavorService {
    private final RecordsFlavorRepository recordsFlavorRepository;
    private final BeerRepository beerRepository;
    private final FlavorRepository flavorRepository;

    @Transactional
    public void save(RecordsFlavorRequest recordsFlavorRequest, Records records){
        List<RecordsFlavor> recordsFlavorList = new ArrayList<>();
        Beer beer = beerRepository.getById(recordsFlavorRequest.getBeerId());
        for(int i=0; i<recordsFlavorRequest.getFlavors().size(); i++){
            recordsFlavorList.add(new RecordsFlavor(
                            beer
                            , records
                            , recordsFlavorRequest.getFlavors().get(i)
                    )
            );
        }
        recordsFlavorRepository.saveAll(recordsFlavorList);
    }
}
