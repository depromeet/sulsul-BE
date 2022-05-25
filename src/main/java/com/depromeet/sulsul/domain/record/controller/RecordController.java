package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.service.RecordService;
import com.depromeet.sulsul.domain.recordFlavor.dto.RecordsFlavorRequest;
import com.depromeet.sulsul.domain.recordFlavor.service.RecordsFlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final RecordsFlavorService recordsFlavorService;
//
//    @PostMapping("/images")
//    public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
//        return ResponseDto.of(recordService.uploadImage(multipartFile));
//    }

    @PostMapping("/records")
    public ResponseEntity<Object> save(@RequestBody RecordRequest recordRequest){
        Record recordSave = recordService.save(recordRequest);
        RecordsFlavorRequest recordsFlavorRequest = new RecordsFlavorRequest(recordRequest.getBeerId(), recordRequest.getFlavors());
        recordsFlavorService.save(recordsFlavorRequest, recordSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/findRecords")
    public ResponseDto<PageableResponse<RecordDto>> findAllRecordsWithPageable(@RequestBody RecordFindRequest recordFindRequest){
        return ResponseDto.of(recordService.findAllRecordsWithPageable(recordFindRequest));
    }

}
