package com.depromeet.sulsul.domain.records.controller;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.records.dto.RecordsDto;
import com.depromeet.sulsul.domain.records.dto.RecordsFindRequest;
import com.depromeet.sulsul.domain.records.dto.RecordsRequest;
import com.depromeet.sulsul.domain.records.entity.Records;
import com.depromeet.sulsul.domain.records.service.RecordsService;
import com.depromeet.sulsul.domain.recordsFlavor.dto.RecordsFlavorRequest;
import com.depromeet.sulsul.domain.recordsFlavor.service.RecordsFlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordsController {

    private final RecordsService recordsService;
    private final RecordsFlavorService recordsFlavorService;

    @PostMapping("/images")
    public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        return ResponseDto.of(recordsService.uploadImage(multipartFile));
    }

    @PostMapping("/record")
    public ResponseEntity<Object> save(@RequestBody RecordsRequest recordsRequest){
        Records recordsSave = recordsService.save(recordsRequest);
        RecordsFlavorRequest recordsFlavorRequest = new RecordsFlavorRequest(recordsRequest.getBeerId(), recordsRequest.getFlavors());
        recordsFlavorService.save(recordsFlavorRequest, recordsSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/record")
    public ResponseDto<PageableResponse<RecordsDto>> findAllRecordsWithPageable(@RequestBody RecordsFindRequest recordsFindRequest){
        return ResponseDto.of(recordsService.findAllRecordsWithPageable(recordsFindRequest));
    }

}
