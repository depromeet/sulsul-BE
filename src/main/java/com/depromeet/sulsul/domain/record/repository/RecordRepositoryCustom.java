package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepositoryCustom {
    List<RecordDto> findAllRecordsWithPageable(Long beerId, Long recordId);

}
