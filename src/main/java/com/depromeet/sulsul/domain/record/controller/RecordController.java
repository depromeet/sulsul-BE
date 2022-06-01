package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordResponseDto;
import com.depromeet.sulsul.domain.record.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "맥주 기록 APIs")
public class RecordController {

  private final RecordService recordService;

  @PostMapping("/images")
  public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
    return ResponseDto.from(recordService.uploadImage(multipartFile));
  }

  @PostMapping
  public ResponseDto<RecordResponseDto> save(@RequestBody RecordRequestDto recordRequestDto) {
    return ResponseDto.from(recordService.save(recordRequestDto));
  }

  @PostMapping("/find")
  @ApiOperation(value = "'이 맥주는 어때요' 관련 맥주 정보 조회 API")
  public ResponseDto<PageableResponseDto<RecordResponseDto>> findAllRecordsWithPageable(
      @RequestBody RecordFindRequestDto recordFindRequestDto) {
    return ResponseDto.from(recordService.findAllRecordsWithPageable(recordFindRequestDto));
  }

  @ApiOperation(value = "기록 삭제 API")
  @DeleteMapping
  public ResponseDto<Long> delete(@RequestParam Long recordId) {
    // TODO : 임시 유저아이디 사용.
    Long memberIdTemp = 1L;
    recordService.delete(recordId, memberIdTemp);
    return ResponseDto.from(recordService.delete(recordId, memberIdTemp));
  }

  @ApiOperation(value = "유저별 기록 수 조회 API")
  @GetMapping("/count/{id}")
  public ResponseDto<Long> findMemberRecordCount(@PathVariable Long id) {
    return ResponseDto.from(recordService.findRecordCountByMemberId(id));
  }
}
