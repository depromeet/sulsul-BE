package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerFilterSortRequest;
import com.depromeet.sulsul.domain.beer.dto.QBeerDto;
import com.depromeet.sulsul.util.PaginationUtil;
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
    public List<BeerDto> findAllWithPageableFilterSort(Long memberId, Long beerId, BeerFilterSortRequest beerFilterSortRequest) {
        JPAQuery<BeerDto> jpaQuery = queryFactory.select(new QBeerDto(country, beer, record.feel, memberBeer))
                .from(beer)
                .leftJoin(record).on(beer.eq(record.beer))
                .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
                .innerJoin(country).on(beer.country.eq(country))
                .fetchJoin()
                .where(beer.id.goe(beerId))
                .limit(PaginationUtil.PAGINATION_SIZE + 1);

        if (beerFilterSortRequest.getBeerTypes() != null) {
            jpaQuery = jpaQuery
                    .where(beer.type.in(beerFilterSortRequest.getBeerTypes()));
        }

        if (beerFilterSortRequest.getCountryIds() != null) {
            jpaQuery = jpaQuery
                    .where(beer.country.id.in(beerFilterSortRequest.getCountryIds()));
        }

        if (beerFilterSortRequest.getSortType() == null) return jpaQuery.fetch();

        switch (beerFilterSortRequest.getSortType()) {
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
}
