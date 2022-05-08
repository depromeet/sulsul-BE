package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<RecordDto> findAllRecordsWithPageable(Long beerId, Long recordId){
        return recordRepository.findAllRecordsWithPageable(beerId, recordId);
    }

}
