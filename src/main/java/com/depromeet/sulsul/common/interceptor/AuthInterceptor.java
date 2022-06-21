package com.depromeet.sulsul.common.interceptor;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthInterceptor implements HandlerInterceptor {

  private final MemberRepository memberRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    log.debug("Checking member id.. request URL : {}", request.getRequestURL());

    long memberIdFromPrincipal = getMemberIdFromPrincipal(
        SecurityContextHolder.getContext().getAuthentication());

    Optional<Member> memberOptional = memberRepository.findById(memberIdFromPrincipal);

    if (memberOptional.isEmpty()) {
      log.debug("There is no member corresponding to the id that exists in the token.");
      response.sendError(SC_UNAUTHORIZED, "There is no member corresponding to the id that exists in the token.");
      return false;
    }

    return true;
  }
}
