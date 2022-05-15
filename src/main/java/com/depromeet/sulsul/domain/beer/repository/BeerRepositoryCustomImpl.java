package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.dto.*;
import com.depromeet.sulsul.util.PaginationUtil;
import com.depromeet.sulsul.util.PropertyUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<BeerDto> findAllWithPageableFilterSort(Long memberId, Long beerId, BeerSearchConditionRequest beerSearchConditionRequest) {
        JPAQuery<BeerDto> jpaQuery = queryFactory.select(new QBeerDto(country, beer, record.feel, memberBeer))
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
                    .where(beer.name.contains(searchKeyword)
                            .or(beer.country.name.contains(searchKeyword))
                            .or(beer.country.continent.name.contains(searchKeyword))
                            .or(beer.content.contains(searchKeyword)));
        }

        if (beerSearchConditionRequest.getSortType() == null) return jpaQuery.fetch();

        switch (beerSearchConditionRequest.getSortType()) {
            case NAME:
                jpaQuery = jpaQuery.orderBy(beer.name.asc());
                break;
            case ALCOHOL:
                jpaQuery = jpaQuery.orderBy(beer.alcohol.asc());
                break;
            case REVIEW:
                jpaQuery = jpaQuery.orderBy(beer.reviews.size().desc());
                break;
        }


        return jpaQuery.fetch();
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
