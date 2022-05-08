package com.depromeet.sulsul.domain.record.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.beerFlavor.dto.BeerFlavorRequest;
import com.depromeet.sulsul.domain.beerFlavor.service.BeerFlavorService;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.service.RecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final BeerFlavorService beerFlavorService;

    @ApiOperation(value="Record, 맛태그 저장 TEST"
            , notes="beerFlavorRequest에는 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/record")
    public ResponseEntity<Object> save(@RequestBody RecordRequest recordRequest
                                       , @RequestBody BeerFlavorRequest beerFlavorRequest){
        Record recordSave = recordService.save(recordRequest);
        beerFlavorService.save(beerFlavorRequest, recordSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/record")
    public ResponseDto<List<RecordDto>> findAllRecordsWithPageable(@RequestParam("beerId") Long beerId
                                                                   , @RequestParam("recordId") Long recordId){
        List<RecordDto> allRecordsWithPageable = recordService.findAllRecordsWithPageable(beerId, recordId);
        return ResponseDto.of(allRecordsWithPageable);
    }
}
