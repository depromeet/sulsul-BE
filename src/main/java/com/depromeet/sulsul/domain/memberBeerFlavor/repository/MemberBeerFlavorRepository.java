package com.depromeet.sulsul.domain.memberBeerFlavor.repository;

import com.depromeet.sulsul.domain.memberBeerFlavor.entity.MemberBeerFlavor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBeerFlavorRepository extends JpaRepository<MemberBeerFlavor, Long>,
    MemberBeerFlavorRepositoryCustom {
}
