package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordsDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final BeerRepository beerRepository;
    private final MemberRepository memberRepository;

//    public ImageDto uploadImage(MultipartFile multipartFile) {
//        if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
//            throw new IllegalArgumentException("[ERROR] Not supported file format.");
//        }
//        return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
//    }

    @Transactional
    public Record save(RecordRequest recordRequest){
        Long memberIdTemp = 1L;
        Member member = memberRepository.getById(memberIdTemp);
        Beer beer = beerRepository.getById(recordRequest.getBeerId());
        return recordRepository.save(new Record(
                member, beer, recordRequest
        ));
    }

    public PageableResponse<RecordDto> findAllRecordsWithPageable(
        RecordFindRequest recordFindRequest){
        List<Record> allRecordWithPageable = recordRepository.findAllRecordsWithPageable(
            recordFindRequest);
        List<RecordDto> allRecordDtosWithPageable = new ArrayList<>();
        PageableResponse<RecordDto> recordDtoPageableResponse = new PageableResponse<>();

        for (Record record : allRecordWithPageable) {
            List<RecordFlavor> recordFlavors = record.getRecordFlavors();
            List<FlavorDto> flavorDtos = new ArrayList<>();
            for (RecordFlavor recordFlavor : recordFlavors) {
                flavorDtos.add(new FlavorDto(recordFlavor.getFlavor().getId(), recordFlavor.getFlavor().getContent()));
            }
            MemberRecordsDto memberRecordsDto = new MemberRecordsDto(record.getMember().getId(), record.getMember().getName());

                allRecordDtosWithPageable.add(new RecordDto(record.getContent(), memberRecordsDto,
                    record.getFeel(), flavorDtos, record.getCreatedAt(), record.getModifiedAt()));
        }
        if(isOverPaginationSize(allRecordDtosWithPageable)){
            allRecordDtosWithPageable.remove(PAGINATION_SIZE);
            recordDtoPageableResponse.setHasNext(true);
        }
        recordDtoPageableResponse.setContents(allRecordDtosWithPageable);
        return recordDtoPageableResponse;
    }
}
