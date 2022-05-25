package com.depromeet.sulsul.domain.recordFlavor.service;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.recordFlavor.dto.RecordsFlavorRequest;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.domain.recordFlavor.repository.RecordsFlavorRepository;
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
    public void save(RecordsFlavorRequest recordsFlavorRequest, Record record){
        List<RecordFlavor> recordFlavorList = new ArrayList<>();
        Beer beer = beerRepository.getById(recordsFlavorRequest.getBeerId());
        for(int i=0; i<recordsFlavorRequest.getFlavors().size(); i++){
            recordFlavorList.add(new RecordFlavor(
//                            beer
                    record
                            , recordsFlavorRequest.getFlavors().get(i)
                    )
            );
        }
        recordsFlavorRepository.saveAll(recordFlavorList);
    }
}
