package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.depromeet.sulsul.domain.record.entity.QRecords.records;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RecordRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Record> findAllRecordsWithPageable(RecordFindRequest recordFindRequest){
        List<Record> recordDtos = queryFactory.select(records)
                .from(records)
                .where(beerIdEq(recordFindRequest.getBeerId())
                        , memberIdEq(recordFindRequest.getMemberId())
                        , recordIdGoe(recordFindRequest.getRecordId())
                )
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
