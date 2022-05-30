package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom,
    QuerydslPredicateExecutor<Record> {
}
