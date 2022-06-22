package com.depromeet.sulsul.oauth2.service;

import static com.depromeet.sulsul.domain.member.dto.RoleType.USER;

import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import com.depromeet.sulsul.domain.memberLevel.repository.MemberLevelRepository;
import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final MemberRepository memberRepository;
  private final MemberLevelRepository memberLevelRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
        .getUserNameAttributeName();

    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

    String socialId = findResourceUniqueId(oAuth2User.getName(), registrationId);

    Map<String, Object> memberMap = saveOrUpdate(attributes, socialId, registrationId);

    Long memberId = (Long) memberMap.get("memberId");
    Boolean isNewMember = (Boolean) memberMap.get("isNewMember");

    CustomOAuth2User customOAuth2User = new CustomOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(USER.getAuthority())),
        attributes.getAttributes(), attributes.getNameAttributeKey(), memberId);

    customOAuth2User.setMemberId(memberId);

    return customOAuth2User;
  }

  @Transactional
  public Map<String, Object> saveOrUpdate(OAuthAttributes oAuthAttributes, String socialId, String registrationId) {

    Map<String, Object> memberMap = new HashMap<>();
    Optional<Member> member = memberRepository.selectBySocial(socialId, registrationId.toUpperCase());

    if (member.isPresent()) {
      member.get().updateRegistrationId(registrationId.toUpperCase());
      memberMap.put("memberId", member.get().getId());
      memberMap.put("email", member.get().getEmail());
      return memberMap;
    }
    MemberLevel memberLevel = memberLevelRepository.findMemberLevelByCount(0L).toEntity();
    Member signUpMember = memberRepository.save(oAuthAttributes.toEntity(socialId, registrationId, memberLevel));
    memberMap.put("memberId", signUpMember.getId());
    memberMap.put("email", signUpMember.getEmail());

    return memberMap;
  }

  private String findResourceUniqueId(String id, String registrationId) {
    switch (registrationId) {
      case "kakao":
        return id;
      case "naver":
        int start = id.indexOf("=") + 1;
        int end = id.indexOf(",");
        return id.substring(start, end);
      default:
        throw new IllegalArgumentException();
    }
  }
}