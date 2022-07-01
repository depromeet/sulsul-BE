package com.depromeet.sulsul.domain.record.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.error.exception.custom.BeerNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.FlavorNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.common.error.exception.custom.NotAllowAccessException;
import com.depromeet.sulsul.common.error.exception.custom.RecordNotFoundException;
import com.depromeet.sulsul.common.external.AwsS3ImageClient;
import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import com.depromeet.sulsul.domain.memberLevel.service.MemberLevelService;
import com.depromeet.sulsul.domain.record.dto.*;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.domain.recordFlavor.repository.RecordFlavorRepository;
import com.depromeet.sulsul.util.ImageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
  private final MemberRepository memberRepository;
  private final MemberLevelService memberLevelService;

  public ImageDto uploadImage(MultipartFile multipartFile) {
    if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
      throw new IllegalArgumentException("[ERROR] Not supported file format.");
    }
    return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
  }

  public RecordResponseDto save(RecordRequestDto recordRequestDto, Long memberId) {

    List<FlavorDto> flavorDtos = new ArrayList<>();

    Record lastSavedRecord = recordRepository.findLastSavedCountryName(memberId).orElse(new Record());

    Beer beer = beerRepository.findById(recordRequestDto.getBeerId())
        .orElseThrow(BeerNotFoundException::new);
    Member member = memberRepository.findById(memberId)
        .orElseThrow(MemberNotFoundException::new);

    Record record = recordRequestDto.toEntity();
    record.setRecord(beer, lastSavedRecord, member);

    Record savedRecord = recordRepository.save(record);

    for (Long flavorId : recordRequestDto.getFlavorIds()) {
      Flavor flavor = flavorRepository.findById(flavorId).orElseThrow(FlavorNotFoundException::new);
      RecordFlavor recordFlavor = RecordFlavor.of(savedRecord, flavor);
      recordFlavorRepository.save(recordFlavor);
      flavorDtos.add(flavor.toDto());
    }

    Long recordCountByMemberId = findRecordCountByMemberId(memberId) + 1;
    MemberLevel memberLevel = memberLevelService.findMemberLevelByCount(recordCountByMemberId)
        .toEntity();
    member.updateLevel(memberLevel);

    return RecordResponseDto.createRecordResponseDto(record, beer, flavorDtos,
        recordRepository.selectCount());
  }

  @Transactional(readOnly = true)
  public RecordResponseDto find(Long recordId, Long memberId) {

    Record record = recordRepository.findById(recordId)
        .orElseThrow(RecordNotFoundException::new);

    validateIsOwner(memberId, record);

    Beer beer = beerRepository.findById(record.getBeer().getId())
        .orElseThrow(BeerNotFoundException::new);

    List<FlavorDto> flavorDtos = new ArrayList<>();

    for (RecordFlavor recordFlavor : record.getRecordFlavors()) {
      flavorDtos.add(recordFlavor.getFlavor().toDto());
    }
    return RecordResponseDto.createRecordResponseDto(record, beer, flavorDtos,
        recordRepository.selectCount());
  }

  public RecordResponseDto update(RecordUpdateRequestDto recordUpdateRequestDto, Long memberId) {

    Record savedRecord = recordRepository.findById(recordUpdateRequestDto.getRecordId())
        .orElseThrow(RecordNotFoundException::new);

    validateIsOwner(memberId, savedRecord);

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
    return RecordResponseDto.createRecordResponseDto(savedRecord, beer, flavorDtos,
        recordRepository.selectCount());
  }

  private void validateIsOwner(Long memberId, Record savedRecord) {
    if (!isOwner(memberId, savedRecord)) {
      throw new NotAllowAccessException();
    }
  }

  private boolean isOwner(Long memberId, Record savedRecord) {
    return Objects.equals(savedRecord.getMember().getId(), memberId);
  }

  @Transactional(readOnly = true)
  public DescPageableResponseDto<RecordResponseDto> findAllRecordsWithPageable(
      RecordFindRequestDto recordFindRequestDto) {
    List<Record> allRecordWithPageable = recordRepository.findAllRecordsWithPageable(
        recordFindRequestDto);
    List<RecordResponseDto> allRecordDtosWithPageableResponse = new ArrayList<>();

    allRecordWithPageable.forEach(record -> {
      List<FlavorDto> flavorDtos = new ArrayList<>();
      record.getRecordFlavors().forEach(recordFlavor -> {
        flavorDtos.add(
            new FlavorDto(recordFlavor.getFlavor().getId(), recordFlavor.getFlavor().getContent()));
      });
      MemberRecordDto memberRecordDto = new MemberRecordDto(record.getMember().getId(),
          record.getMember().getNickname());

      allRecordDtosWithPageableResponse.add(
          new RecordResponseDto(record, memberRecordDto, flavorDtos));
    });

    Long resultCount = findRecordCountByBeerId(recordFindRequestDto.getBeerId());
    Long cursor = allRecordDtosWithPageableResponse.isEmpty() ? null
        : allRecordDtosWithPageableResponse.get(allRecordDtosWithPageableResponse.size() - 1)
            .getId();
    return DescPageableResponseDto.of(resultCount, allRecordDtosWithPageableResponse
        , cursor, PAGINATION_SIZE);
  }

  @Transactional(readOnly = true)
  public Long findRecordCountByBeerId(Long beerId) {
    return recordRepository.findRecordCountByBeerId(beerId);
  }

  public Long delete(Long recordId, Long memberId) {
    Record targetRecord = recordRepository.getById(recordId);
    targetRecord.delete();
    validateIsOwner(memberId, targetRecord);
    return recordId;
  }

  @Transactional(readOnly = true)
  public Long findRecordCountByMemberId(Long id) {
    return recordRepository.findRecordCountByMemberId(id);
  }

  @Transactional(readOnly = true)
  public DescPageableResponseDto<RecordTicketResponseDto> findAllRecordsTicketWithPageable(
      Long recordId, Long memberId) {
    List<RecordTicketResponseDto> allRecordsTicketWithPageable = recordRepository.findAllRecordsTicketWithPageable(
        recordId, memberId);

    Long resultCount = findRecordCountByMemberId(memberId);

    Long cursor = allRecordsTicketWithPageable.isEmpty() ? null
        : allRecordsTicketWithPageable.get(allRecordsTicketWithPageable.size() - 1).getId();
    return DescPageableResponseDto.of(resultCount, allRecordsTicketWithPageable, cursor,
        PAGINATION_SIZE);
  }

  @Transactional(readOnly = true)
  public RecordCountryAndCountResponseDto findCountryAndCountByMemberId(Long memberId) {
    RecordRecentCountryDto recordRecentCountryDto = recordRepository.findRecordRecentCountryByMemberId(memberId);
    Long recordCountByRecentCountry = recordRepository.findRecordCountByRecentCountry(recordRecentCountryDto, memberId);

    return new RecordCountryAndCountResponseDto(recordRecentCountryDto, recordCountByRecentCountry);
  }


  public void updateDeletedAtByMemberId(Long id) {
    recordRepository.updateDeletedAtByMemberId(id);
  }
}
