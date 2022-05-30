package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordResponseDto {

  private String content;
  private Integer feel;
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

  public RecordResponseDto(String content, MemberRecordDto memberRecordDto, Integer feel, List<FlavorDto> flavorDtos,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.content = content;
    this.feel = feel;
    this.flavorDtos = flavorDtos;
    this.memberRecordDto = memberRecordDto;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Builder
  public RecordResponseDto(String content, Integer feel, LocalDateTime createdAt, String startCountryKor,
      String startCountryEng, String endCountryKor, String endCountryEng) {
    this.content = content;
    this.feel = feel;
    this.createdAt = createdAt;
    this.startCountryKor = startCountryKor;
    this.startCountryEng = startCountryEng;
    this.endCountryKor = endCountryKor;
    this.endCountryEng = endCountryEng;
  }

  public static RecordResponseDto toDto(Record record) {
    return RecordResponseDto.builder()
        .content(record.getContent())
        .feel(record.getFeel())
        .createdAt(record.getCreatedAt())
        .startCountryKor(record.getStartCountryKor())
        .startCountryEng(record.getStartCountryEng())
        .endCountryKor(record.getEndCountryKor())
        .endCountryEng(record.getEndCountryEng())
        .build();
  }

  public void updateFlavors(List<FlavorDto> flavorDtos) {
    this.flavorDtos = flavorDtos;
  }

  public void updateRecordCount(Long recordCount) {
    this.recordCount = recordCount;
  }

  public void updateBeerResponseDto(BeerResponseDto beerResponseDto) {
    this.beerResponseDto = beerResponseDto;
  }
}
