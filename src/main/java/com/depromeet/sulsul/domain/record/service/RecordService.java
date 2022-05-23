package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.error.exception.custom.BeerNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.FlavorNotFoundException;
import com.depromeet.sulsul.common.external.AwsS3ImageClient;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.entity.RecordFlavor;
import com.depromeet.sulsul.domain.record.repository.RecordFlavorRepository;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {

  private final AwsS3ImageClient awsS3ImageClient;
  private final RecordRepository recordRepository;
  private final BeerRepository beerRepository;
  private final FlavorRepository flavorRepository;
  private final RecordFlavorRepository recordFlavorRepository;

  public ImageDto uploadImage(MultipartFile multipartFile) {
    if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
      throw new IllegalArgumentException("[ERROR] Not supported file format.");
    }
    return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
  }

  public void findAll() {

  }

  public void save(RecordRequest recordRequest) {

    Record record = recordRepository.save(recordRequest.toEntity());

    for (Long flavorId : recordRequest.getFlavorIds()) {
        Flavor flavor = flavorRepository.findById(flavorId).orElseThrow(FlavorNotFoundException::new);
        RecordFlavor recordFlavor = RecordFlavor.of(record, flavor);

      recordFlavorRepository.save(recordFlavor);
    }
  }
}
