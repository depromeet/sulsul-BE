package com.depromeet.sulsul.oauth2.service;

import static com.depromeet.sulsul.domain.member.dto.RoleType.USER;

import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final MemberRepository memberRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    // OAuth2 서비스 id (구글, 카카오, 네이버)
    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());

    saveOrUpdate(attributes);

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(USER.getAuthority())),
        attributes.getAttributes(), attributes.getNameAttributeKey());
  }

  private void saveOrUpdate(OAuthAttributes attributes) {
    Member member = memberRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getName(), attributes.getEmail()))
        .orElse(attributes.toEntity());
    memberRepository.save(member);
  }
}
