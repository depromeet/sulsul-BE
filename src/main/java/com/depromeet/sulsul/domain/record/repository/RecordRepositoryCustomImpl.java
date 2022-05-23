package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordResponse;
import org.springframework.data.domain.Page;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom{
    @Override
    public Page<RecordResponse> findAllByMemberId(Long memberId) {
        return null;
    }
}
