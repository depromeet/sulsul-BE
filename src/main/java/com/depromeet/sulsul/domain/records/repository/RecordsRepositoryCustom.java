package com.depromeet.sulsul.domain.records.repository;

import com.depromeet.sulsul.domain.records.dto.RecordsFindRequest;
import com.depromeet.sulsul.domain.records.entity.Records;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepositoryCustom {
    List<Records> findAllRecordsWithPageable(RecordsFindRequest recordFindRequest);
}
