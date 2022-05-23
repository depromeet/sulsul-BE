package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordResponse;
import org.springframework.data.domain.Page;

public interface RecordRepositoryCustom {

    Page<RecordResponse> findAllByMemberId(Long memberId);
}
