package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordResponseDto {

  private Long id;
  private String content;
  private Integer feel;
  private String imageUrl;
  private MemberRecordDto memberRecordDto;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String startCountryKor;
  private String startCountryEng;
  private String endCountryKor;
  private String endCountryEng;
  private List<FlavorDto> flavorDtos = new ArrayList<>();
  private BeerResponseDto beerResponseDto;
  private Long recordCount;
  private Boolean isPublic;

  public RecordResponseDto(Record record, MemberRecordDto memberRecordDto, List<FlavorDto> flavorDtos) {
    this.id = record.getId();
    this.content = record.getContent();
    this.feel = record.getFeel();
    this.imageUrl = record.getImageUrl();
    this.createdAt = record.getCreatedAt();
    this.updatedAt = record.getUpdatedAt();
    if(record.getStartCountryKor().isBlank()){
      this.startCountryKor = "대한민국";
    }else{
      this.startCountryKor = record.getStartCountryKor();
    }

    if(record.getStartCountryEng().isBlank()){
      this.startCountryEng = "KOR";
    }else{
      this.startCountryEng = record.getStartCountryEng();
    }
    this.endCountryKor = record.getStartCountryKor();
    this.endCountryEng = record.getEndCountryEng();
    this.isPublic = record.getIsPublic();
    this.memberRecordDto = memberRecordDto;
    this.flavorDtos = flavorDtos;
  }

  public RecordResponseDto(Record record, List<FlavorDto> flavorDtos) {
    this.id = record.getId();
    this.content = record.getContent();
    this.feel = record.getFeel();
    this.imageUrl = record.getImageUrl();
    this.createdAt = record.getCreatedAt();
    this.updatedAt = record.getUpdatedAt();
    this.startCountryKor = record.getStartCountryKor();
    this.startCountryEng = record.getStartCountryEng();
    this.endCountryKor = record.getStartCountryKor();
    this.endCountryEng = record.getEndCountryEng();
    this.isPublic = record.getIsPublic();
    this.flavorDtos = flavorDtos;
  }

  public static RecordResponseDto createRecordResponseDto(Record record, Beer beer, List<FlavorDto> flavorDtos, Long recordCount){
    RecordResponseDto recordResponseDto = new RecordResponseDto(record, flavorDtos);
    recordResponseDto.updateBeerResponseDto(Beer.toDto(beer));
    recordResponseDto.updateFlavors(flavorDtos);
    recordResponseDto.updateRecordCount(recordCount);
    return recordResponseDto;
  }

  private void updateFlavors(List<FlavorDto> flavorDtos) {
    this.flavorDtos = flavorDtos;
  }

  private void updateRecordCount(Long recordCount) {
    this.recordCount = recordCount;
  }

  private void updateBeerResponseDto(BeerResponseDto beerResponseDto) {
    this.beerResponseDto = beerResponseDto;
  }
}
