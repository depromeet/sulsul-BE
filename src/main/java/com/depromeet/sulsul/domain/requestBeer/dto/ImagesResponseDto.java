package com.depromeet.sulsul.domain.requestBeer.dto;

import com.depromeet.sulsul.common.dto.ImageDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImagesResponseDto {
  private List<ImageDto> imageDtos;
}
