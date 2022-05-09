package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.flavor.entity.QFlavor;
import com.depromeet.sulsul.domain.member.entity.QMember;
import com.depromeet.sulsul.domain.record.dto.QRecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.entity.QRecord;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.recordFlavor.entity.QRecordFlavor;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import java.util.List;

import static com.depromeet.sulsul.domain.flavor.entity.QFlavor.*;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;
import static com.depromeet.sulsul.domain.recordFlavor.entity.QRecordFlavor.*;

@Repository
public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RecordRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Record> findAllRecordsWithPageable(Long beerId, Long recordId){
        List<Record> recordDtos = queryFactory.select(record)
                .from(record)
                .where(record.id.goe(recordId))
                .limit(PaginationUtil.PAGINATION_SIZE+1)
                .fetch();
        return recordDtos;
    }

}
