package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.QBeer;
import com.depromeet.sulsul.domain.member.entity.QMember;
import com.depromeet.sulsul.util.PaginationUtil;
import com.depromeet.sulsul.util.PropertyUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.depromeet.sulsul.domain.beer.entity.QBeer.beer;

@Repository
public class BeerRepositoryCustomImpl implements BeerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BeerRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Beer> findByIdWithPageable(Long beerId) {
        return queryFactory.select(beer)
                .from(beer)
                .where(beer.id.goe(beerId))
                .limit(PaginationUtil.PAGINATION_SIZE + 1)
                .fetch();
    }
}
