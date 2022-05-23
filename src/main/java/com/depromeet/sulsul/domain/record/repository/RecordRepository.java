package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {
}
