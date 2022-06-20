package com.depromeet.sulsul.domain.requestBeer.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.error.exception.custom.RequestSizeExceedException;
import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerFindDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerRequestDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.facade.RequestBeerFacade;
import com.depromeet.sulsul.domain.requestBeer.service.RequestBeerService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/request-beers")
@RequiredArgsConstructor
@Api(tags = "미등록 맥주 APIs")
public class RequestBeerController {

  private final RequestBeerService requestBeerService;
  private final RequestBeerFacade requestBeerFacade;

  @PostMapping
  public ResponseDto<RequestBeerResponseDto> save(
      @RequestBody RequestBeerRequestDto requestBeerRequestDto, Authentication authentication) {
    return ResponseDto.from(
        requestBeerService.save(requestBeerRequestDto, getMemberIdFromPrincipal(authentication)));
  }

  @PostMapping("/list")
  public DescPageableResponseDto<RequestBeerResponseDto> find(
      @RequestBody RequestBeerFindDto requestBeerFindDto, Authentication authentication) {
    if(requestBeerFindDto.getCursor()==0) requestBeerFindDto.setCursor(null);
    return requestBeerService.find(requestBeerFindDto, getMemberIdFromPrincipal(authentication));
  }

  @Validated
  @PostMapping("/images")
  public ResponseDto<List<ImageDto>> uploadImages(
      @RequestParam("files") List<MultipartFile> multipartFiles) {
    if (multipartFiles.size() > 2) {
      throw new RequestSizeExceedException();
    }
    return ResponseDto.from(requestBeerFacade.uploadImage(multipartFiles).getImageDtos());
  }
}
