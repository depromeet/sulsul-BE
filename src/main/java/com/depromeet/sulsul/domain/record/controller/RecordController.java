package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beerFlavor.dto.BeerFlavorRequest;
import com.depromeet.sulsul.domain.beerFlavor.service.BeerFlavorService;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final BeerFlavorService beerFlavorService;

    @PostMapping("/record")
    public ResponseEntity<Object> save(@RequestBody RecordRequest recordRequest
                                       , @RequestBody BeerFlavorRequest beerFlavorRequest){
        Record recordSave = recordService.save(recordRequest);
        beerFlavorService.save(beerFlavorRequest, recordSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/record")
    public ResponseDto<PageableResponse<RecordDto>> findAllRecordsWithPageable(@RequestBody RecordFindRequest recordFindRequest){

        return ResponseDto.of(recordService.findAllRecordsWithPageable(recordFindRequest));
    }
}
