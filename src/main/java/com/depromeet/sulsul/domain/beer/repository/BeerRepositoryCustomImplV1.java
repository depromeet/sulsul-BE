package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.QBeerDto;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.depromeet.sulsul.domain.QMemberBeer.memberBeer;
import static com.depromeet.sulsul.domain.beer.entity.QBeer.beer;
import static com.depromeet.sulsul.domain.country.entity.QCountry.country;
import static com.depromeet.sulsul.domain.record.entity.QRecord.record;

@Repository
public class BeerRepositoryCustomImplV1 implements BeerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BeerRepositoryCustomImplV1(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<BeerDto> findByIdWithPageableV1(Long memberId, Long beerId) {
        return queryFactory.select(new QBeerDto(country, beer, record.feel, memberBeer))
                .from(beer)
                .leftJoin(record).on(beer.eq(record.beer))
                .leftJoin(memberBeer).on(beer.eq(memberBeer.beer).and(memberBeer.member.id.eq(memberId)))
                .innerJoin(country).on(beer.country.eq(country))
                .fetchJoin()
                .where(beer.id.goe(beerId))
                .limit(PaginationUtil.PAGINATION_SIZE + 1)
                .fetch();
    }
}
