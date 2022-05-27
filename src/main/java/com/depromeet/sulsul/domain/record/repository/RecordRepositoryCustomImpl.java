package com.depromeet.sulsul.domain.record.repository;

import com.depromeet.sulsul.common.entity.QBaseEntity;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequest;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public RecordRepositoryCustomImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<Record> findAllRecordsWithPageable(RecordFindRequest recordFindRequest) {
    List<Record> recordDtos = queryFactory.select(record)
        .from(record)
        .where(beerIdEq(recordFindRequest.getBeerId())
            , memberIdEq(recordFindRequest.getMemberId())
            , recordIdGoe(recordFindRequest.getRecordId())
            , record.deletedAt.isNull()
        )
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
    return recordDtos;
  }

  private BooleanExpression beerIdEq(Long beerId) {
    return beerId != null ? record.beer.id.eq(beerId) : null;
  }

  private BooleanExpression memberIdEq(Long memberId) {
    return memberId != null ? record.member.id.eq(memberId) : null;
  }

  private BooleanExpression recordIdGoe(Long recordId) {
    return recordId != null ? record.id.goe(recordId) : null;
  }
}
