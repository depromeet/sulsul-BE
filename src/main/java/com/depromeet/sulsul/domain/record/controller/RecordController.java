package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.dto.RecordResponse;
import com.depromeet.sulsul.domain.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public Page<RecordResponse> findAll(){

        recordService.findAll();

        return null;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody RecordRequest recordRequest) {
        recordService.save(recordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/images")
    public ResponseDto<ImageDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        return ResponseDto.of(recordService.uploadImage(multipartFile));
    }
}
