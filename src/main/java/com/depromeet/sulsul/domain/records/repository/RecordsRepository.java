package com.depromeet.sulsul.domain.records.repository;

import com.depromeet.sulsul.domain.records.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Long>, RecordsRepositoryCustom, QuerydslPredicateExecutor<Records> {
}
