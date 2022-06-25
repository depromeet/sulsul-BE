package com.depromeet.sulsul.domain.beer.repository;

import static com.depromeet.sulsul.common.request.SortCondition.ALCOHOL_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.ALCOHOL_DESC;
import static com.depromeet.sulsul.common.request.SortCondition.ID_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.ID_DESC;
import static com.depromeet.sulsul.common.request.SortCondition.NAME_ENG_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.NAME_ENG_DESC;
import static com.depromeet.sulsul.common.request.SortCondition.NAME_KOR_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.NAME_KOR_DESC;
import static com.depromeet.sulsul.common.request.SortCondition.RECORD_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.RECORD_DESC;
import static com.depromeet.sulsul.common.request.SortCondition.UPDATED_AT_ASC;
import static com.depromeet.sulsul.common.request.SortCondition.UPDATED_AT_DESC;
import static com.depromeet.sulsul.domain.beer.entity.QBeer.beer;
import static com.depromeet.sulsul.domain.country.entity.QCountry.country;
import static com.depromeet.sulsul.domain.memberBeer.entity.QMemberBeer.memberBeer;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

import com.depromeet.sulsul.common.request.Filter;
import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.request.SortCondition;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseWithCountDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.depromeet.sulsul.domain.beer.dto.QBeerResponseDto;
import com.depromeet.sulsul.util.PaginationUtil;
import com.depromeet.sulsul.util.PropertyUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class BeerRepositoryCustomImpl implements BeerRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public BeerRepositoryCustomImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<BeerResponseDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest) {
    JPAQuery<BeerResponseDto> jpaQuery = queryFactory.select(
            new QBeerResponseDto(country, beer, record.feel, memberBeer)).from(beer).leftJoin(record)
        .on(beer.eq(record.beer)).leftJoin(memberBeer)
        .on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId))).innerJoin(country)
        .on(beer.country.eq(country)).fetchJoin()
        .where(beer.id.goe(beerId).and(beer.deletedAt.isNull()))
        .limit(PaginationUtil.PAGINATION_SIZE + 1L);

    if (beerSearchConditionRequest.getBeerTypes() != null) {
      jpaQuery = jpaQuery.where(beer.type.in(beerSearchConditionRequest.getBeerTypes()));
    }

    if (beerSearchConditionRequest.getCountryIds() != null) {
      jpaQuery = jpaQuery.where(beer.country.id.in(beerSearchConditionRequest.getCountryIds()));
    }

    if (!PropertyUtil.isEmpty(beerSearchConditionRequest.getSearchKeyword())) {
      String searchKeyword = beerSearchConditionRequest.getSearchKeyword();
      jpaQuery = jpaQuery.where(searchBooleanExpression(searchKeyword));
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
  public List<BeerResponseDto> findPageWith(Long memberId, ReadRequest readRequest) {

    Filter filter = readRequest.getFilter();
    List<SortCondition> sortBy = readRequest.getSortBy();

    JPAQuery<BeerResponseDto> jpaQuery = queryFactory.select(
            new QBeerResponseDto(country, beer, record.feel, memberBeer)).from(beer).leftJoin(record)
        .on(beer.eq(record.beer)).leftJoin(memberBeer)
        .on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId))).innerJoin(country)
        .on(beer.country.eq(country)).fetchJoin().where(beer.deletedAt.isNull())
        .limit(readRequest.getLimit() + 1L);

    jpaQuery = addOffset(jpaQuery, readRequest.getCursor());

    jpaQuery = addBeerTypesFilter(jpaQuery, filter);

    jpaQuery = addCountryIdsFilter(jpaQuery, filter);

    jpaQuery = jpaQuery.where(searchBooleanExpression(readRequest.getQuery()));

    jpaQuery = addOrderByWith(jpaQuery, sortBy);

    return jpaQuery.fetch();
  }

  @Override
  public BeerResponseWithCountDto findPageWithV2(ReadRequest readRequest) {

    Filter filter = readRequest.getFilter();
    List<SortCondition> sortBy = readRequest.getSortBy();

    JPAQuery<BeerResponseDto> jpaQuery = queryFactory.select(new QBeerResponseDto(country, beer))
        .from(beer).innerJoin(country).on(beer.country.eq(country)).fetchJoin()
        .where(beer.deletedAt.isNull());

    jpaQuery = addBeerTypesFilter(jpaQuery, filter);

    jpaQuery = addCountryIdsFilter(jpaQuery, filter);

    jpaQuery = jpaQuery.where(searchBooleanExpression(readRequest.getQuery()));

    jpaQuery = addOrderByWith(jpaQuery, sortBy);

    return new BeerResponseWithCountDto(jpaQuery.fetch().size(),
        addOffset(jpaQuery, readRequest.getCursor()).limit(readRequest.getLimit() + 1L).fetch());
  }

  @Override
  public BeerResponseWithCountDto findPageWithV2(Long memberId, ReadRequest readRequest) {

    Filter filter = readRequest.getFilter();
    List<SortCondition> sortBy = readRequest.getSortBy();

    JPAQuery<BeerResponseDto> jpaQuery = queryFactory.select(
            new QBeerResponseDto(country, beer, record.feel.max(), memberBeer)).from(beer)
        .leftJoin(record)
        .on(beer.eq(record.beer).and(record.member.id.eq(memberId)).and(record.deletedAt.isNull()))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country)).where(
            beer.deletedAt.isNull().and(memberBeer.isNotNull().and(memberBeer.deletedAt.isNull())))
        .groupBy(country, beer, memberBeer).fetchJoin();

    jpaQuery = addBeerTypesFilter(jpaQuery, filter);

    jpaQuery = addCountryIdsFilter(jpaQuery, filter);

    jpaQuery = jpaQuery.where(searchBooleanExpression(readRequest.getQuery()));

    jpaQuery = addOrderByWith(jpaQuery, sortBy);

    return new BeerResponseWithCountDto(jpaQuery.fetch().size(),
        addOffset(jpaQuery, readRequest.getCursor()).limit(readRequest.getLimit() + 1L).fetch());
  }

  private JPAQuery<BeerResponseDto> addOffset(JPAQuery<BeerResponseDto> jpaQuery, Long cursor) {
    if (cursor == null) {
      return jpaQuery;
    }
    return jpaQuery.offset(cursor);
  }

  private JPAQuery<BeerResponseDto> addOrderByWith(JPAQuery<BeerResponseDto> jpaQuery,
      List<SortCondition> sortBy) {
    if (CollectionUtils.isEmpty(sortBy)) {
      return jpaQuery;
    }
    for (SortCondition sortCondition : sortBy) {
      jpaQuery = appendDynamicBuilderWith(jpaQuery, sortCondition);
    }
    return jpaQuery;
  }

  private JPAQuery<BeerResponseDto> addBeerTypesFilter(JPAQuery<BeerResponseDto> jpaQuery,
      Filter filter) {
    if (filter != null && !CollectionUtils.isEmpty(filter.getBeerTypes())) {
      jpaQuery = jpaQuery.where(beer.type.in(filter.getBeerTypes()));
    }
    return jpaQuery;
  }

  private JPAQuery<BeerResponseDto> addCountryIdsFilter(JPAQuery<BeerResponseDto> jpaQuery,
      Filter filter) {
    if (filter != null && !CollectionUtils.isEmpty(filter.getCountryIds())) {
      jpaQuery = jpaQuery.where(beer.country.id.in(filter.getCountryIds()));
    }
    return jpaQuery;
  }

  private BooleanExpression searchBooleanExpression(String searchKeyword) {
    if (PropertyUtil.isEmpty(searchKeyword)) {
      return null;
    }
    return beer.nameKor.contains(searchKeyword).or(beer.nameEng.contains(searchKeyword))
        .or(beer.country.nameKor.contains(searchKeyword))
        .or(beer.country.nameEng.contains(searchKeyword))
        .or(beer.country.continent.name.contains(searchKeyword))
        .or(beer.content.contains(searchKeyword));
  }

  @Override
  public List<BeerResponseDto> findPageWith(Long memberId) {

    return queryFactory.select(new QBeerResponseDto(country, beer, record.feel, memberBeer))
        .from(beer).leftJoin(record).on(beer.eq(record.beer)).leftJoin(memberBeer)
        .on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId))).innerJoin(country)
        .on(beer.country.eq(country)).fetchJoin().limit(PaginationUtil.PAGINATION_SIZE + 1L)
        .fetch();
  }

  @Override
  public List<BeerResponseDto> findPageWith() {

    return queryFactory.select(new QBeerResponseDto(country, beer, null, null)).from(beer)
        .innerJoin(country).on(beer.country.eq(country)).fetchJoin()
        .limit(PaginationUtil.PAGINATION_SIZE + 1L).fetch();
  }

  @Override
  public List<BeerResponseDto> findBeers() {
    return queryFactory.select(new QBeerResponseDto(country, beer)).from(beer).innerJoin(country)
        .on(beer.country.eq(country)).where(beer.deletedAt.isNull()).fetch();
  }

  @Override
  public List<BeerResponseDto> findBeerNotExistsRecord(Long memberId) {

    return queryFactory.select(new QBeerResponseDto(country, beer, record.feel, memberBeer))
        .from(beer).leftJoin(record).on(beer.eq(record.beer).and(record.member.id.eq(memberId)))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .where(beer.deletedAt.isNull().and(record.isNull())).fetchJoin().fetch();
  }

  @Override
  public List<BeerResponseDto> findBeerLikedByMemberId(Long memberId) {
    return queryFactory.select(new QBeerResponseDto(country, beer, record.feel.max(), memberBeer))
        .from(beer).leftJoin(record).on(beer.eq(record.beer).and(record.member.id.eq(memberId)))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .where(beer.deletedAt.isNull().and(memberBeer.isNotNull()))
        .groupBy(country, beer, memberBeer).fetchJoin().fetch();
  }

  @Override
  public BeerResponseWithCountDto findBeerLikedByMemberIdV2(Long memberId, ReadRequest request) {
    List<SortCondition> sortBy = request.getSortBy();

    JPAQuery<BeerResponseDto> jpaQuery = queryFactory.select(
            new QBeerResponseDto(country, beer, record.feel.max(), memberBeer)).from(beer)
        .leftJoin(record).on(beer.eq(record.beer).and(record.member.id.eq(memberId)))
        .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
        .innerJoin(country).on(beer.country.eq(country))
        .where(beer.deletedAt.isNull().and(memberBeer.isNotNull()))
        .groupBy(country, beer, memberBeer).fetchJoin();

    jpaQuery = addOrderByWith(jpaQuery, sortBy);

    return new BeerResponseWithCountDto(jpaQuery.fetch().size(),
        addOffset(jpaQuery, request.getCursor()).limit(request.getLimit() + 1L).fetch());
  }

  private JPAQuery<BeerResponseDto> appendDynamicBuilderWith(JPAQuery<BeerResponseDto> jpaQuery,
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
  public Tuple findById(Long beerId) {
    return queryFactory.select(country, beer).from(beer).innerJoin(country)
        .on(beer.country.eq(country)).where(beer.id.eq(beerId).and(beer.deletedAt.isNull()))
        .fetchFirst();
  }

  @Override
  public Tuple findById(Long memberId, Long beerId) {
    return queryFactory.select(country, beer, memberBeer).from(beer).leftJoin(memberBeer)
        .on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId))).innerJoin(country)
        .on(beer.country.eq(country)).where(beer.id.eq(beerId).and(beer.deletedAt.isNull()))
        .fetchFirst();
  }

  @Override
  public Long findBeerCountByMemberId(Long id) {
    return queryFactory.select(record.beer.id).from(record).leftJoin(record.beer, beer)
        .where(record.member.id.eq(id), beer.deletedAt.isNull(), record.deletedAt.isNull()).stream()
        .distinct().count();
  }

  public Integer countWithFilter(ReadRequest readRequest) {

    BooleanBuilder byFilterTypeInAndCountryIdInAndQueryLike = new BooleanBuilder();

    Filter filter = readRequest.getFilter();
    String query = readRequest.getQuery();

    if (filter != null && !CollectionUtils.isEmpty(filter.getBeerTypes())) {
      byFilterTypeInAndCountryIdInAndQueryLike.and(beer.type.in(filter.getBeerTypes()));
    }

    if (filter != null && !CollectionUtils.isEmpty(filter.getCountryIds())) {
      byFilterTypeInAndCountryIdInAndQueryLike.and(beer.country.id.in(filter.getCountryIds()));
    }

    if (!PropertyUtil.isEmpty(query)) {
      byFilterTypeInAndCountryIdInAndQueryLike.and(searchBooleanExpression(query));
    }

    return queryFactory.select(beer).from(beer)
        .where(byFilterTypeInAndCountryIdInAndQueryLike, beer.deletedAt.isNull()).fetch().size();
  }
}
