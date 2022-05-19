package com.depromeet.sulsul.domain.records.repository;

import com.depromeet.sulsul.domain.records.dto.QRecordsDto;
import com.depromeet.sulsul.domain.records.dto.RecordsDto;
import com.depromeet.sulsul.domain.records.dto.RecordsFindRequest;
import com.depromeet.sulsul.domain.records.entity.QRecords;
import com.depromeet.sulsul.domain.records.entity.Records;
import com.depromeet.sulsul.domain.recordsFlavor.entity.QRecordsFlavor;
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
import static com.depromeet.sulsul.domain.records.entity.QRecords.records;
import static com.depromeet.sulsul.domain.recordsFlavor.entity.QRecordsFlavor.*;

public class RecordsRepositoryCustomImpl implements RecordsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RecordsRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Records> findAllRecordsWithPageable(RecordsFindRequest recordFindRequest){
        List<Records> recordDtos = queryFactory.select(records)
                .from(records)
                .where(beerIdEq(recordFindRequest.getBeerId())
                        , memberIdEq(recordFindRequest.getMemberId())
                        , recordIdGoe(recordFindRequest.getRecordId()))
                .limit(PaginationUtil.PAGINATION_SIZE+1)
                .fetch();
        return recordDtos;
    }

    private BooleanExpression beerIdEq(Long beerId){
        return beerId != null ? records.beer.id.eq(beerId) : null;
    }

    private BooleanExpression memberIdEq(Long memberId){
        return memberId != null ? records.member.id.eq(memberId) : null;
    }

    private BooleanExpression recordIdGoe(Long recordId){
        return recordId != null ? records.id.goe(recordId) : null;
    }
}
