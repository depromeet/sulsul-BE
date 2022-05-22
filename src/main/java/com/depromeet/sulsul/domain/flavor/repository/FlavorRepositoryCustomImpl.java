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
}
