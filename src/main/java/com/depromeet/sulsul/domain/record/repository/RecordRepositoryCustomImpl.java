package com.depromeet.sulsul.domain.record.repository;

import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;

public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public RecordRepositoryCustomImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto) {
    return queryFactory.select(record)
        .from(record)
        .where(beerIdEq(recordFindRequestDto.getBeerId())
            , memberIdEq(recordFindRequestDto.getMemberId())
            , recordIdGoe(recordFindRequestDto.getRecordId())
            , record.deletedAt.isNull()
        )
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
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
