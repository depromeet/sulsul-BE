package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.querydsl.core.Tuple;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public interface RecordRepositoryCustom {

  List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto);

  Long findRecordCountByMemberId(Long id);

  List<RecordTicketResponseDto> findAllRecordsTicketWithPageable(Long recordId, Long memberId);

  RecordCountryAndCountResponseDto findRecordCountryAndCountResponseDto(Long memberId);

  Tuple findEndCountryOfRecordByMemberId(Long id);

  Record findLastSavedCountryName(Long memberId);

  Long selectCount();

  Long findRecordCountByBeerId(Long beerId);

  void updateDeletedAtByMemberId(Long id);
}
