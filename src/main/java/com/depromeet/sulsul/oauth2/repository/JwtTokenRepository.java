package com.depromeet.sulsul.oauth2.repository;

import com.depromeet.sulsul.oauth2.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<Token,Long> {
}
