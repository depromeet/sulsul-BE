package com.depromeet.sulsul.oauth2.service;

import com.depromeet.sulsul.domain.member.dto.RoleType;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

  private static final String KAKAO = "kakao";
  private static final String NAVER = "naver";

  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name,
      String email) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;

  }

  public static OAuthAttributes of(String registrationId, String nameAttributeKey, Map<String, Object> attributes) {
    switch (registrationId) {
      case KAKAO:
        return ofKakao(nameAttributeKey, attributes);
      case NAVER:
        return ofNaver(nameAttributeKey, attributes);
      default:
        throw new IllegalArgumentException();
    }
  }

  private static OAuthAttributes ofNaver(String nameAttributeKey, Map<String, Object> attributes) {

    Map<String, Object> response = (Map<String, Object>) attributes.get(
        "response");    // 네이버에서 받은 데이터에서 프로필 정보다 담긴 response 값을 꺼낸다.

    return OAuthAttributes.builder()
        .name((String) response.get("name"))
        .email((String) response.get("email"))
        .attributes(attributes)
        .nameAttributeKey(nameAttributeKey)
        .build();
  }

  private static OAuthAttributes ofKakao(String nameAttributeKey,
      Map<String, Object> attributes) {

    Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) response.get("profile");

    return OAuthAttributes.builder()
        .name((String) profile.get("nickname"))
        .email((String) response.get("email"))
        .attributes(attributes)
        .nameAttributeKey(nameAttributeKey)
        .build();
  }

  public Member toEntity(String socialId, String registrationId, MemberLevel memberLevel) {
    return Member.builder()
        .email(email)
        .role(RoleType.USER)
        .socialId(socialId)
        .socialType(registrationId)
        .memberLevel(memberLevel)
        .build();
  }
}
