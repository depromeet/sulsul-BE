package com.depromeet.sulsul.domain.record.repository;


import static com.depromeet.sulsul.domain.member.entity.QMember.member;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

import com.depromeet.sulsul.domain.member.entity.QMember;
import com.depromeet.sulsul.domain.record.dto.QRecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.QRecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto, Long memberId) {
    return queryFactory.select(record)
        .from(record)
        .where(beerIdEq(recordFindRequestDto.getBeerId())
            , memberIdEq(memberId)
            , recordIdGoe(recordFindRequestDto.getRecordId())
            , record.deletedAt.isNull()
        )
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
  }

  @Override
  public Long findRecordCountByMemberId(Long id) {
    return queryFactory
            .selectFrom(record)
            .leftJoin(record.member, member)
            .where(record.member.id.eq(id), record.deletedAt.isNull())
            .stream()
            .count();
  }

  @Override
  public List<RecordTicketResponseDto> findAllRecordsTicketWithPageable(Long recordId, Long memberId) {
    return queryFactory.select(new QRecordTicketResponseDto(
            record.id, record.beer.nameKor, record.beer.nameEng, record.createdAt, record.feel
            , record.startCountryEng, record.endCountryEng, record.startCountryKor, record.endCountryKor
        )).from(record)
        .where(
            memberIdEq(memberId)
            , recordIdGoe(recordId)
            , record.deletedAt.isNull()
        )
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
  }

  @Override
  public RecordCountryAndCountResponseDto findRecordCountryAndCountResponseDto(Long memberId){
    return queryFactory.select(new QRecordCountryAndCountResponseDto(record.endCountryEng, record.count()))
        .from(record)
        .where(
            record.endCountryEng.eq(
                queryFactory.select(record.endCountryEng)
                    .from(record)
                    .orderBy(record.createdAt.desc())
                    .fetchFirst()
            )
        )
        .fetchOne();
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
