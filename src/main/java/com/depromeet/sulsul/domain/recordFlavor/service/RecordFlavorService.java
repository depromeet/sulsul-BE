package com.depromeet.sulsul.domain.recordFlavor.service;

import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.recordFlavor.dto.RecordFlavorRequest;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.domain.recordFlavor.repository.RecordFlavorRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class RecordFlavorService {

  private final RecordFlavorRepository recordFlavorRepository;

  @Transactional
  public void save(RecordFlavorRequest recordFlavorRequest, Record record) {
    List<RecordFlavor> recordFlavorList = new ArrayList<>();
    int flavorSize = recordFlavorRequest.getFlavors().size();
    for (int i = 0; i < flavorSize; i++) {
      recordFlavorList.add(new RecordFlavor(
              record
              , recordFlavorRequest.getFlavors().get(i)
          )
      );
    }
    recordFlavorRepository.saveAll(recordFlavorList);
  }
}
