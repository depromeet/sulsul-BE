package com.depromeet.sulsul.domain.country.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.depromeet.sulsul.domain.beer.entity.QBeer.beer;
import static com.depromeet.sulsul.domain.country.entity.QCountry.country;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

@RequiredArgsConstructor
public class CountryRepositoryCustomImpl implements CountryRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Long findCountryCountByMemberId(Long id) {
    return queryFactory
        .select(record.beer.country.id)
        .from(record)
        .leftJoin(record.beer, beer)
        .leftJoin(beer.country, country)
        .where(record.member.id.eq(id)
              ,record.deletedAt.isNull() )
        .stream()
        .distinct()
        .count();

  }
}
