package com.depromeet.sulsul.domain.member.service;

import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.dto.NicknameRequestDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final RestTemplate restTemplate;

  @Transactional(readOnly = true)
  public MemberDto findById(final long id) {
    return memberRepository.selectById(id).orElseThrow(MemberNotFoundException::new);
  }

  public String updateNickname(Long id, NicknameRequestDto nicknameRequestDto) {
    Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    member.updateNickname(nicknameRequestDto.getNickname());
    return member.getNickname();
  }

  public void updateDeletedAtById(Long id) {
    memberRepository.updateDeletedAtById(id);
  }

  public String findByRandomNickname() {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(
        "https://nickname.hwanmoo.kr/?format=text&count=1");
    return Objects.requireNonNull(restTemplate.exchange(
            uriBuilder.toUriString(),
            HttpMethod.GET,
            getHttpEntity(),
            String.class)
        .getBody());
  }

  private HttpEntity<HttpHeaders> getHttpEntity() {
    return new HttpEntity<>(getHttpHeaders());
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
