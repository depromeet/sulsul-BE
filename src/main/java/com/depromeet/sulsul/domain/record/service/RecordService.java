package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final BeerRepository beerRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Record save(RecordRequest recordRequest){
        return recordRepository.save(new Record(
                recordRequest.getContent()
                , recordRequest.getFeel()
                , recordRequest.getScore()
                , memberRepository.getById(recordRequest.getMemberId())
                , beerRepository.getById(recordRequest.getBeerId())
        ));
    }

    public PageableResponse<RecordDto> findAllRecordsWithPageable(RecordFindRequest recordFindRequest){
        List<Record> allRecordsWithPageable = recordRepository.findAllRecordsWithPageable(recordFindRequest);
        List<RecordDto> allRecordDtosWithPageable = new ArrayList<>();
        PageableResponse<RecordDto> recordDtoPageableResponse = new PageableResponse<>();
        for (Record record : allRecordsWithPageable) {
            List<RecordFlavor> recordFlavors = record.getRecordFlavors();
            List<Flavor> flavors = new ArrayList<>();
            for (RecordFlavor recordFlavor : recordFlavors) {
                flavors.add(recordFlavor.getFlavor());
            }
            MemberRecordDto memberRecordDto = new MemberRecordDto(record.getMember().getId(), record.getMember().getName());

            allRecordDtosWithPageable.add(new RecordDto(record.getContent(), memberRecordDto,
                    record.getFeel(), record.getScore(), flavors, record.getCreatedDate(), record.getLastModifiedDate()));
        }
        if(isOverPaginationSize(allRecordDtosWithPageable)){
            allRecordDtosWithPageable.remove(PAGINATION_SIZE);
            recordDtoPageableResponse.setHasNext(true);
        }
        recordDtoPageableResponse.setContents(allRecordDtosWithPageable);
        return recordDtoPageableResponse;
    }

}
