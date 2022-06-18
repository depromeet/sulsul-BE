package com.depromeet.sulsul.domain.record.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordUpdateRequestDto;
import com.depromeet.sulsul.domain.record.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@Api(tags = "기록 APIs (version 1)")
public class RecordController {

  private final RecordService recordService;

  @PostMapping("/images")
  public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
    return ResponseDto.from(recordService.uploadImage(multipartFile));
  }

  @ApiOperation(value = "기록 작성 API")
  @PostMapping
  public ResponseDto<RecordResponseDto> save(@RequestBody RecordRequestDto recordRequestDto,
      Authentication authentication) {
    return ResponseDto.from(
        recordService.save(recordRequestDto, getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "작성 기록 상세보기 API")
  @GetMapping("/{recordId}")
  public ResponseDto<RecordResponseDto> find(
      @PathVariable(name = "recordId", required = false) Long recordId,
      Authentication authentication) {
    return ResponseDto.from(recordService.find(recordId, getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "기록 업데이트 API")
  @PatchMapping("/{recordId}")
  public ResponseDto<RecordResponseDto> update(
      @RequestBody RecordUpdateRequestDto recordUpdateRequestDto,
      Authentication authentication) {
    return ResponseDto.from(
        recordService.update(recordUpdateRequestDto, getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "기록 삭제 API")
  @DeleteMapping("/{recordId}")
  public ResponseDto<Long> delete(@PathVariable Long recordId, Authentication authentication) {
    recordService.delete(recordId, getMemberIdFromPrincipal(authentication));
    return ResponseDto.from(
        recordService.delete(recordId, getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "'이 맥주는 어때요' 관련 맥주 정보 조회 API")
  @PostMapping("/find")
  public DescPageableResponseDto<RecordResponseDto> findAllRecordsWithPageable(
      @RequestBody RecordFindRequestDto recordFindRequestDto) {
    return recordService.findAllRecordsWithPageable(recordFindRequestDto);
  }


  @ApiOperation(value = "유저별 기록 수 조회 API")
  @GetMapping("/count")
  public ResponseDto<Long> findMemberRecordCount(Authentication authentication) {
    return ResponseDto.from(recordService.findRecordCountByMemberId(getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "기록 작성 맥주 티켓 조회 API")
  @GetMapping(value = {"/tickets/{recordId}", "/tickets"})
  public DescPageableResponseDto<RecordTicketResponseDto> findAllRecordsTicketWithPageable(
      @PathVariable(name = "recordId", required = false) Long recordId,
      Authentication authentication) {
    return recordService.findAllRecordsTicketWithPageable(recordId,
        getMemberIdFromPrincipal(authentication));
  }

  @ApiOperation(value = "해당 유저 최신 작성 record 국가 개수 조회 API")
  @GetMapping("/recently-visited-country")
  public ResponseDto<RecordCountryAndCountResponseDto> findCountryAndCountByMemberId(
      Authentication authentication) {
    return ResponseDto.from(
        recordService.findCountryAndCountByMemberId(getMemberIdFromPrincipal(authentication)));
  }
}
