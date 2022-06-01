package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.querydsl.core.Tuple;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public interface RecordRepositoryCustom {

  List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto);

  Tuple findEndCountryOfRecordByMemberId(Long id);

  Long findRecordCountByMemberId(Long id);

  Record findLastSavedCountryName();

  Long selectCount();
}
