package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.external.AwsS3ImageClient;
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

  public ImageDto uploadImage(MultipartFile multipartFile) {
    if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
      throw new IllegalArgumentException("[ERROR] Not supported file format.");
    }
    return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
  }
}
