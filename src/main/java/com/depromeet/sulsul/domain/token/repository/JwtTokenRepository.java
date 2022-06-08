package com.depromeet.sulsul.domain.token.repository;

import com.depromeet.sulsul.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<Token,Long> {
}
