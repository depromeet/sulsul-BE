package com.depromeet.sulsul.domain.record.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

import com.depromeet.sulsul.common.external.AwsS3ImageClient;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordResponseDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RecordService {

  private final AwsS3ImageClient awsS3ImageClient;
  private final RecordRepository recordRepository;
  private final BeerRepository beerRepository;
  private final MemberRepository memberRepository;

  // TODO : 에러 출력. 이후 변경예정
//    public ImageDto uploadImage(MultipartFile multipartFile) {
//        if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
//            throw new IllegalArgumentException("[ERROR] Not supported file format.");
//        }
//        return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
//    }

  @Transactional
  public Record save(RecordRequestDto recordRequestDto, Long memberId) {
    Member member = memberRepository.getById(memberId);
    Beer beer = beerRepository.getById(recordRequestDto.getBeerId());
    return recordRepository.save(new Record(
        member, beer, recordRequestDto
    ));
  }

  public PageableResponseDto<RecordResponseDto> findAllRecordsWithPageable(
      RecordFindRequestDto recordFindRequestDto) {
    List<Record> allRecordWithPageable = recordRepository.findAllRecordsWithPageable(
        recordFindRequestDto);
    List<RecordResponseDto> allRecordDtosWithPageableResponse = new ArrayList<>();
    PageableResponseDto<RecordResponseDto> recordDtoPageableResponse = new PageableResponseDto<>();

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
    if (isOverPaginationSize(allRecordDtosWithPageableResponse)) {
      allRecordDtosWithPageableResponse.remove(PAGINATION_SIZE);
      recordDtoPageableResponse.setHasNext(true);
    }
    recordDtoPageableResponse.setContents(allRecordDtosWithPageableResponse);
    return recordDtoPageableResponse;
  }

  // Todo : 로그인 구현 이후 유저 validation 로직 추가 예정
  @Transactional
  public void delete(Long recordId, Long memberId) {
    Record targetRecord = recordRepository.getById(recordId);
    targetRecord.delete();
  }

  public Long findMemberRecordCount(Long id) {
    return recordRepository.findMemberRecordCount(id);
  }
}
