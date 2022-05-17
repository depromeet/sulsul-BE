package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.flavor.entity.QFlavor;
import com.depromeet.sulsul.domain.member.entity.QMember;
import com.depromeet.sulsul.domain.record.dto.QRecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.entity.QRecord;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.recordFlavor.entity.QRecordFlavor;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
    public List<Record> findAllRecordsWithPageable(RecordFindRequest recordFindRequest){
        List<Record> recordDtos = queryFactory.select(record)
                .from(record)
                .where(beerIdEq(recordFindRequest.getBeerId())
                     , memberIdEq(recordFindRequest.getMemberId())
                     , recordIdGoe(recordFindRequest.getRecordId()))
                .limit(PaginationUtil.PAGINATION_SIZE+1)
                .fetch();
        return recordDtos;
    }

    private BooleanExpression beerIdEq(Long beerId){
        return beerId != null ? record.beer.id.eq(beerId) : null;
    }

    private BooleanExpression memberIdEq(Long memberId){
        return memberId != null ? record.member.id.eq(memberId) : null;
    }

    private BooleanExpression recordIdGoe(Long recordId){
        return recordId != null ? record.id.goe(recordId) : null;
    }
}
