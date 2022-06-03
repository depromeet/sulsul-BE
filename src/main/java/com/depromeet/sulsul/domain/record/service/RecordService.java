package com.depromeet.sulsul.domain.record.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.error.exception.custom.BeerNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.FlavorNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.RecordNotFoundException;
import com.depromeet.sulsul.common.external.AwsS3ImageClient;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.dto.RecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordUpdateRequestDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.domain.recordFlavor.repository.RecordFlavorRepository;
import com.depromeet.sulsul.util.ImageUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RecordService {

  private final AwsS3ImageClient awsS3ImageClient;
  private final RecordRepository recordRepository;
  private final BeerRepository beerRepository;
  private final FlavorRepository flavorRepository;
  private final RecordFlavorRepository recordFlavorRepository;

  public ImageDto uploadImage(MultipartFile multipartFile) {
    if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
      throw new IllegalArgumentException("[ERROR] Not supported file format.");
    }
    return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
  }

  public RecordResponseDto save(RecordRequestDto recordRequestDto) {

    List<FlavorDto> flavorDtos = new ArrayList<>();

    Record lastSavedRecord = recordRepository.findLastSavedCountryName();
    Beer beer = beerRepository.findById(recordRequestDto.getBeerId())
        .orElseThrow(BeerNotFoundException::new);

    Record record = recordRequestDto.toEntity();
    record.updateBeer(beer);
    record.updateStartCountry(lastSavedRecord);
    record.updateEndCountry(beer);

    Record savedRecord = recordRepository.save(record);

    for (Long flavorId : recordRequestDto.getFlavorIds()) {
      Flavor flavor = flavorRepository.findById(flavorId).orElseThrow(FlavorNotFoundException::new);
      RecordFlavor recordFlavor = RecordFlavor.of(savedRecord, flavor);
      recordFlavorRepository.save(recordFlavor);
      flavorDtos.add(flavor.toDto());
    }

    return RecordResponseDto.createRecordResponseDto(record, beer, flavorDtos, recordRepository.selectCount());
  }

  @Transactional(readOnly = true)
  public RecordResponseDto find(Long recordId, Long memberId){

    Record record = recordRepository.findById(recordId)
        .orElseThrow(RecordNotFoundException::new);;

    Beer beer = beerRepository.findById(record.getBeer().getId())
        .orElseThrow(BeerNotFoundException::new);

    List<FlavorDto> flavorDtos = new ArrayList<>();

    for(RecordFlavor recordFlavor : record.getRecordFlavors()){
      flavorDtos.add(recordFlavor.getFlavor().toDto());
    }
    return RecordResponseDto.createRecordResponseDto(record, beer, flavorDtos, recordRepository.selectCount());
  }

  public RecordResponseDto update(RecordUpdateRequestDto recordUpdateRequestDto, Long memeberId){

    // update가 이루어질 record 찾아오기
    Record savedRecord = recordRepository.findById(recordUpdateRequestDto.getRecordId())
        .orElseThrow(RecordNotFoundException::new);

    Beer beer = beerRepository.findById(savedRecord.getBeer().getId())
        .orElseThrow(BeerNotFoundException::new);

    List<RecordFlavor> recordFlavors = new ArrayList<>();
    List<FlavorDto> flavorDtos = new ArrayList<>();

    for (Long flavorId : recordUpdateRequestDto.getFlavorIds()) {
      Flavor flavor = flavorRepository.findById(flavorId).orElseThrow(FlavorNotFoundException::new);
      RecordFlavor recordFlavor = RecordFlavor.of(savedRecord, flavor);
      recordFlavors.add(recordFlavor);
      flavorDtos.add(recordFlavor.getFlavor().toDto());
    }

    savedRecord.updateRecord(recordUpdateRequestDto, recordFlavors);
    return RecordResponseDto.createRecordResponseDto(savedRecord, beer, flavorDtos, recordRepository.selectCount());
  }

  @Transactional(readOnly = true)
  public PageableResponseDto<RecordResponseDto> findAllRecordsWithPageable(
      RecordFindRequestDto recordFindRequestDto, Long memberId) {
    List<Record> allRecordWithPageable = recordRepository.findAllRecordsWithPageable(
        recordFindRequestDto, memberId);
    List<RecordResponseDto> allRecordDtosWithPageableResponse = new ArrayList<>();

    for (Record record : allRecordWithPageable) {
      List<RecordFlavor> recordFlavors = record.getRecordFlavors();
      List<FlavorDto> flavorDtos = new ArrayList<>();
      for (RecordFlavor recordFlavor : recordFlavors) {
        flavorDtos.add(
            new FlavorDto(recordFlavor.getFlavor().getId(), recordFlavor.getFlavor().getContent()));
      }
      MemberRecordDto memberRecordDto = new MemberRecordDto(record.getMember().getId(),
          record.getMember().getName());

      allRecordDtosWithPageableResponse.add(
          new RecordResponseDto(record.getContent(), memberRecordDto,
              record.getFeel(), flavorDtos, record.getCreatedAt(), record.getUpdatedAt()));
    }
    return PageableResponseDto.of(allRecordDtosWithPageableResponse, recordFindRequestDto.getRecordId(), PAGINATION_SIZE);
  }

  // Todo : 로그인 구현 이후 유저 validation 로직 추가 예정
  @Transactional
  public Long delete(Long recordId, Long memberId) {
    Record targetRecord = recordRepository.getById(recordId);
    targetRecord.delete();
    return recordId;
  }

  @Transactional(readOnly = true)
  public Long findRecordCountByMemberId(Long id) {
    return recordRepository.findRecordCountByMemberId(id);
  }

  public PageableResponseDto<RecordTicketResponseDto> findAllRecordsTicketWithPageable(Long recordId, Long memberId){
    List<RecordTicketResponseDto> allRecordsTicketWithPageable = recordRepository.findAllRecordsTicketWithPageable(recordId, memberId);
    return PageableResponseDto.of(allRecordsTicketWithPageable, recordId, PAGINATION_SIZE);
  }

  public RecordCountryAndCountResponseDto findCountryAndCountByMemberId(Long memberId){
    return recordRepository.findRecordCountryAndCountResponseDto(memberId);
  }

}
