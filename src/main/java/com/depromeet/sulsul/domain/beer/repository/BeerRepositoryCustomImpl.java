package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.Filter;
import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.request.SortCondition;
import com.depromeet.sulsul.domain.beer.dto.*;
import com.depromeet.sulsul.util.PaginationUtil;
import com.depromeet.sulsul.util.PropertyUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import org.springframework.util.CollectionUtils;

import static com.depromeet.sulsul.common.request.SortCondition.*;
import static com.depromeet.sulsul.domain.QMemberBeer.memberBeer;
import static com.depromeet.sulsul.domain.beer.entity.QBeer.beer;
import static com.depromeet.sulsul.domain.country.entity.QCountry.country;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

@Repository
public class BeerRepositoryCustomImpl implements BeerRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public BeerRepositoryCustomImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<BeerDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest) {
    JPAQuery<BeerDto> jpaQuery = queryFactory.select(
            new QBeerDto(country, beer, record.feel, memberBeer))
        .from(beer)
        .leftJoin(record).on(beer.eq(record.beer))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .fetchJoin()
        .where(beer.id.goe(beerId))
        .limit(PaginationUtil.PAGINATION_SIZE + 1);

    if (beerSearchConditionRequest.getBeerTypes() != null) {
      jpaQuery = jpaQuery
          .where(beer.type.in(beerSearchConditionRequest.getBeerTypes()));
    }

    if (beerSearchConditionRequest.getCountryIds() != null) {
      jpaQuery = jpaQuery
          .where(beer.country.id.in(beerSearchConditionRequest.getCountryIds()));
    }

    if (!PropertyUtil.isEmpty(beerSearchConditionRequest.getSearchKeyword())) {
      String searchKeyword = beerSearchConditionRequest.getSearchKeyword();
      jpaQuery = jpaQuery
          .where(beer.nameKor.contains(searchKeyword)
              .or(beer.nameEng.contains(searchKeyword))
              .or(beer.country.nameKor.contains(searchKeyword))
              .or(beer.country.nameEng.contains(searchKeyword))
              .or(beer.country.continent.name.contains(searchKeyword))
              .or(beer.content.contains(searchKeyword)));
    }

      if (beerSearchConditionRequest.getSortType() == null) {
          return jpaQuery.fetch();
      }

    switch (beerSearchConditionRequest.getSortType()) {
      case NAME:
        jpaQuery = jpaQuery.orderBy(beer.nameKor.asc());
        break;
      case ALCOHOL:
        jpaQuery = jpaQuery.orderBy(beer.alcohol.asc());
        break;
      case REVIEW:
        jpaQuery = jpaQuery.orderBy(beer.records.size().desc());
        break;
    }

    return jpaQuery.fetch();
  }

  @Override
  public List<BeerDto> findPageWith(Long memberId, ReadRequest readRequest) {

    Filter filter = readRequest.getFilter();
    List<SortCondition> sortBy = readRequest.getSortBy();
    Long cursor = readRequest.getCursor();

    JPAQuery<BeerDto> jpaQuery = queryFactory.select(
            new QBeerDto(country, beer, record.feel, memberBeer))
        .from(beer)
        .leftJoin(record).on(beer.eq(record.beer))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .fetchJoin()
//            .where(beer.id.goe(cursor))  // TODO: No-Offset 기반의 최적화
        .limit(readRequest.getLimit() + 1);

    if (cursor != null) {
      jpaQuery = jpaQuery.offset(cursor);
    }

    if (filter != null && !CollectionUtils.isEmpty(filter.getBeerTypes())) {
      jpaQuery = jpaQuery
          .where(beer.type.in(filter.getBeerTypes()));
    }

    if (filter != null && !CollectionUtils.isEmpty(filter.getCountryIds())) {
      jpaQuery = jpaQuery
          .where(beer.country.id.in(filter.getCountryIds()));
    }

    if (!PropertyUtil.isEmpty(readRequest.getQuery())) {
      String searchKeyword = readRequest.getQuery();
      jpaQuery = jpaQuery
          .where(beer.nameKor.contains(searchKeyword)
              .or(beer.nameEng.contains(searchKeyword))
              .or(beer.country.nameKor.contains(searchKeyword))
              .or(beer.country.nameEng.contains(searchKeyword))
              .or(beer.country.continent.name.contains(searchKeyword))
              .or(beer.content.contains(searchKeyword)));
    }

      if (CollectionUtils.isEmpty(sortBy)) {
          return jpaQuery.fetch();
      }

    for (SortCondition sortCondition : sortBy) {
      jpaQuery = appendDynamicBuilderWith(jpaQuery, sortCondition);

    }

    return jpaQuery.fetch();

  }

  private JPAQuery<BeerDto> appendDynamicBuilderWith(JPAQuery<BeerDto> jpaQuery,
      SortCondition sortCondition) {
    if (sortCondition == ID_ASC) {
      return jpaQuery.orderBy(beer.id.asc());
    }
    if (sortCondition == ID_DESC) {
      return jpaQuery.orderBy(beer.id.desc());
    }
    if (sortCondition == NAME_KOR_ASC) {
      return jpaQuery.orderBy(beer.nameKor.asc());
    }
    if (sortCondition == NAME_KOR_DESC) {
      return jpaQuery.orderBy(beer.nameKor.desc());
    }
    if (sortCondition == NAME_ENG_ASC) {
      return jpaQuery.orderBy(beer.nameEng.asc());
    }
    if (sortCondition == NAME_ENG_DESC) {
      return jpaQuery.orderBy(beer.nameEng.desc());
    }
    if (sortCondition == ALCOHOL_ASC) {
      return jpaQuery.orderBy(beer.alcohol.asc());
    }
    if (sortCondition == ALCOHOL_DESC) {
      return jpaQuery.orderBy(beer.alcohol.desc());
    }
    if (sortCondition == RECORD_ASC) {
      return jpaQuery.orderBy(beer.records.size().asc());
    }
    if (sortCondition == RECORD_DESC) {
      return jpaQuery.orderBy(beer.records.size().desc());
    }
    if (sortCondition == UPDATED_AT_ASC) {
      return jpaQuery.orderBy(beer.updatedAt.asc());
    }
    if (sortCondition == UPDATED_AT_DESC) {
      return jpaQuery.orderBy(beer.updatedAt.desc());
    }
    return jpaQuery;
  }

  @Override
  public BeerDetail findById(Long memberId, Long beerId) {
    return queryFactory.select(new QBeerDetail(country, beer, memberBeer))
        .from(beer)
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .where(beer.id.eq(beerId))
        .fetchOne();
  }
}
