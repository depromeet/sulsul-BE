package com.depromeet.sulsul.domain.requestBeer.facade;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.domain.record.service.RecordService;
import com.depromeet.sulsul.domain.requestBeer.dto.ImagesResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional
@Service
public class RequestBeerFacade {

  private final RecordService recordService;

  public ImagesResponseDto uploadImage(List<MultipartFile> multipartFiles) {

    List<ImageDto> imageDtos = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {
      imageDtos.add(recordService.uploadImage(multipartFile));
    }

    return new ImagesResponseDto(imageDtos);
  }
}
