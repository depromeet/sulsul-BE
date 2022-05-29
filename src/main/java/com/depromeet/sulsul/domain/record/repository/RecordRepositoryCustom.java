package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepositoryCustom {

  List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto);

    Long findMemberRecordCount(Long id);
}
