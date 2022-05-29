package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordResponseDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.service.RecordService;
import com.depromeet.sulsul.domain.recordFlavor.dto.RecordFlavorRequest;
import com.depromeet.sulsul.domain.recordFlavor.service.RecordFlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

  private final RecordService recordService;
  private final RecordFlavorService recordFlavorService;

  //  TODO : 에러 출력. 이후 변경예정
//    @PostMapping("/images")
//    public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
//        return ResponseDto.of(recordService.uploadImage(multipartFile));
//    }

  @PostMapping("/records")
  public ResponseEntity<Object> save(@RequestBody RecordRequestDto recordRequestDto) {
    Long memberIdTemp = 1L;
    Record recordSave = recordService.save(recordRequestDto, memberIdTemp);
    RecordFlavorRequest recordFlavorRequest = new RecordFlavorRequest(recordRequestDto.getBeerId(),
        recordRequestDto.getFlavors());
    recordFlavorService.save(recordFlavorRequest, recordSave);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/records/find")
  public ResponseDto<PageableResponseDto<RecordResponseDto>> findAllRecordsWithPageable(
      @RequestBody RecordFindRequestDto recordFindRequestDto) {
    return ResponseDto.from(recordService.findAllRecordsWithPageable(recordFindRequestDto));
  }

  @DeleteMapping("/records")
  public ResponseEntity<Object> delete(@RequestParam Long recordId) {
    // TODO : 임시 유저아이디 사용.
    Long memberIdTemp = 1L;
    recordService.delete(recordId, memberIdTemp);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/count/{id}")
  public ResponseDto<Long> findMemberRecordCount(@PathVariable Long id){
   return ResponseDto.from(recordService.findRecordCountByMemberId(id));
  }
}
