package com.depromeet.sulsul.oauth2.service;

import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.depromeet.sulsul.domain.member.dto.RoleType.USER;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final MemberRepository memberRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

    Map<String, Object> memberMap = saveOrUpdate(attributes, registrationId);
    Long memberId = (Long) memberMap.get("memberId");
    String email = (String) memberMap.get("email");

    CustomOAuth2User customOAuth2User = new CustomOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(USER.getAuthority())),
        attributes.getAttributes(), attributes.getNameAttributeKey(), memberId, email);

    customOAuth2User.setMemberId(memberId);

    return customOAuth2User;
  }

  @Transactional
  public Map<String, Object> saveOrUpdate(OAuthAttributes attributes, String registrationId) {

    Map<String, Object> memberMap = new HashMap<>();
    Optional<Member> member = memberRepository.selectByEmailAndSocial(attributes.getEmail(), registrationId.toUpperCase());

    if (member.isPresent()) {
      member.get().updateEmail(attributes.getEmail());
      member.get().updateRegistrationId(registrationId.toUpperCase());
      memberMap.put("memberId", member.get().getId());
      memberMap.put("email", member.get().getEmail());
      return memberMap;
    }

    Member signUpMember = memberRepository.save(attributes.toEntity(registrationId));
    memberMap.put("memberId", signUpMember.getId());
    memberMap.put("email", signUpMember.getEmail());

    return memberMap;
  }
}
