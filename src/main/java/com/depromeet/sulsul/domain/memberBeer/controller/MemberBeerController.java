package com.depromeet.sulsul.domain.memberBeer.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.memberBeer.service.MemberBeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer/heart")
@Api(tags = "찜하기 관련 API")
public class MemberBeerController {

  private final MemberBeerService memberBeerService;

  @PostMapping("/{beerId}")
  @ApiOperation(value = "찜하기 API")
  public ResponseDto<Boolean> save(@PathVariable("beerId") Long beerId) {
    Long memberId = 1L; // TODO : 임시 유저아이디 생성
    return ResponseDto.from(memberBeerService.save(beerId, memberId));
  }

  @ApiOperation(value = "찜하기 취소 API")
  @DeleteMapping("/{beerId}")
  public ResponseDto<Boolean> delete(@PathVariable("beerId") Long beerId) {
    Long memberId = 1L; // TODO : 임시 유저아이디 생성
    return ResponseDto.from(memberBeerService.delete(beerId, memberId));
  }
}
