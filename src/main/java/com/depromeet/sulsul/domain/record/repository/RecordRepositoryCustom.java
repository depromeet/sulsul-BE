package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepositoryCustom {

  List<Record> findAllRecordsWithPageable(RecordFindRequest recordFindRequest);
}
