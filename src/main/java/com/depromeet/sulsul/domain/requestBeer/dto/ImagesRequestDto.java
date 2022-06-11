package com.depromeet.sulsul.domain.requestBeer.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ImagesRequestDto {

  @Valid @Size(max = 2)
  private List<MultipartFile> files;
}
