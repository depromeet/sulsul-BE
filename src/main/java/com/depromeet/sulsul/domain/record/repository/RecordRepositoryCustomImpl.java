package com.depromeet.sulsul.domain.record.repository;


import static com.depromeet.sulsul.domain.member.entity.QMember.member;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

import com.depromeet.sulsul.domain.record.dto.QRecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.QRecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordCountryAndCountResponseDto;
import com.depromeet.sulsul.domain.record.dto.RecordFindRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordTicketResponseDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Record> findAllRecordsWithPageable(RecordFindRequestDto recordFindRequestDto) {
    return queryFactory.select(record)
        .from(record)
        .where(beerIdEq(recordFindRequestDto.getBeerId())
            , recordIdLoe(recordFindRequestDto.getRecordId())
            , record.deletedAt.isNull()
            , record.isPublic.isTrue()
        )
        .orderBy(record.id.desc())
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
  }

  @Override
  public Tuple findEndCountryOfRecordByMemberId(Long id) {
    return queryFactory.select(record.endCountryKor, record.endCountryEng)
        .from(record)
        .innerJoin(record.member, member)
        .where(record.deletedAt.isNull())
        .orderBy(record.id.desc())
        .fetchFirst();
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
  public Optional<Record> findLastSavedCountryName(Long memberId) {
    return Optional.ofNullable(queryFactory
        .select(record)
        .from(record)
        .where(memberIdEq(memberId))
        .orderBy(record.id.desc())
        .fetchFirst());
  }

  @Override
  public Long selectCount() {
    return queryFactory
        .selectFrom(record)
        .where(record.deletedAt.isNull()
              , record.isPublic.isTrue()
        )
        .stream()
        .count();
  }

  @Override
  public List<RecordTicketResponseDto> findAllRecordsTicketWithPageable(Long recordId, Long memberId) {
    return queryFactory.select(new QRecordTicketResponseDto(
            record.id, record.beer, record.createdAt, record.feel
            , record.startCountryEng, record.endCountryEng, record.startCountryKor, record.endCountryKor, record.imageUrl
        )).from(record)
        .where(
            memberIdEq(memberId)
            , recordIdLoe(recordId)
            , record.deletedAt.isNull()
        )
        .orderBy(record.id.desc())
        .limit(PaginationUtil.PAGINATION_SIZE + 1)
        .fetch();
  }

  @Override
  public RecordCountryAndCountResponseDto findRecordCountryAndCountResponseDto(Long memberId){
    return queryFactory.select(new QRecordCountryAndCountResponseDto(record.endCountryKor, record.endCountryEng, record.count()))
        .from(record)
        .where(
            record.endCountryEng.eq(
                queryFactory.select(record.endCountryEng)
                    .from(record)
                    .where(
                          record.deletedAt.isNull()
                          , memberIdEq(memberId)
                    )
                    .orderBy(record.id.desc())
                    .fetchFirst()
            )
            , record.deletedAt.isNull()
            , memberIdEq(memberId)
        )
        .groupBy(record.endCountryEng, record.endCountryKor)
        .fetchOne();
  }

  @Override
  public Long findRecordCountByBeerId(Long beerId){
    return queryFactory.selectFrom(record)
        .where(beerIdEq(beerId)
              , record.deletedAt.isNull()
              , record.isPublic.isTrue()
        ).stream().count();
  }

  @Override
  public void updateDeletedAtByMemberId(Long id) {
    queryFactory.update(record)
        .set(record.deletedAt, LocalDateTime.now())
        .where(record.member.id.eq(id))
        .execute();
  }

  private BooleanExpression beerIdEq(Long beerId) {
    return beerId != null ? record.beer.id.eq(beerId) : null;
  }

  private BooleanExpression memberIdEq(Long memberId) {
    return memberId != null ? record.member.id.eq(memberId) : null;
  }

  private BooleanExpression recordIdLoe(Long recordId) {
    return recordId != null ? record.id.loe(recordId) : null;
  }

}
