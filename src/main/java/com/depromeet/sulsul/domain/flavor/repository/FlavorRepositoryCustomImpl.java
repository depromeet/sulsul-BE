package com.depromeet.sulsul.domain.flavor.repository;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.dto.QFlavorResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.depromeet.sulsul.domain.flavor.entity.QFlavor.flavor;

@RequiredArgsConstructor
public class FlavorRepositoryCustomImpl implements FlavorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FlavorResponse> selectAll() {
        return jpaQueryFactory.select(new QFlavorResponse(flavor.id, flavor.content)).from(flavor).fetch();
    }
  
    @Override
  public List<FlavorResponseDto> findTopThreeFlavorsByCount(Long beerId){
    return queryFactory.select(new QFlavorResponseDto(flavor.content, flavor.count()))
        .from(flavor)
        .innerJoin(recordFlavor).on(flavor.eq(recordFlavor.flavor))
        .innerJoin(record).on(record.eq(recordFlavor.record))
        .innerJoin(beer).on(beer.eq(record.beer))
        .fetchJoin()
        .where(
            beerIdEq(beerId)
        )
        .limit(3)
        .groupBy(flavor.id)
        .orderBy(flavor.count().desc())
        .fetch();
  }

  private BooleanExpression beerIdEq(Long beerId) {
    return beerId != null ? beer.id.eq(beerId) : null;
  }
}
