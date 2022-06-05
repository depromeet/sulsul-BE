package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.record.entity.Record;
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

  public RecordResponseDto(Long id, String content, MemberRecordDto memberRecordDto, Integer feel,
      List<FlavorDto> flavorDtos,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.content = content;
    this.feel = feel;
    this.flavorDtos = flavorDtos;
    this.memberRecordDto = memberRecordDto;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public RecordResponseDto(Long id, String content, Integer feel, String imageUrl,
      MemberRecordDto memberRecordDto, LocalDateTime createdAt, LocalDateTime updatedAt,
      String startCountryKor, String startCountryEng, String endCountryKor,
      String endCountryEng,
      List<FlavorDto> flavorDtos) {
    this.id = id;
    this.content = content;
    this.feel = feel;
    this.imageUrl = imageUrl;
    this.memberRecordDto = memberRecordDto;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.startCountryKor = startCountryKor;
    this.startCountryEng = startCountryEng;
    this.endCountryKor = endCountryKor;
    this.endCountryEng = endCountryEng;
    this.flavorDtos = flavorDtos;
  }

  @Builder
  public RecordResponseDto(Long id, String content, Integer feel, String imageUrl,
      LocalDateTime createdAt,
      String startCountryKor, String startCountryEng, String endCountryKor, String endCountryEng) {
    this.id = id;
    this.content = content;
    this.feel = feel;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.startCountryKor = startCountryKor;
    this.startCountryEng = startCountryEng;
    this.endCountryKor = endCountryKor;
    this.endCountryEng = endCountryEng;
  }

  public static RecordResponseDto toDto(Record record) {
    return RecordResponseDto.builder()
        .id(record.getId())
        .content(record.getContent())
        .feel(record.getFeel())
        .imageUrl(record.getImageUrl())
        .createdAt(record.getCreatedAt())
        .startCountryKor(record.getStartCountryKor())
        .startCountryEng(record.getStartCountryEng())
        .endCountryKor(record.getEndCountryKor())
        .endCountryEng(record.getEndCountryEng())
        .build();
  }

  public static RecordResponseDto createRecordResponseDto(Record record, Beer beer, List<FlavorDto> flavorDtos, Long recordCount){
    RecordResponseDto recordResponseDto = RecordResponseDto.toDto(record);
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
