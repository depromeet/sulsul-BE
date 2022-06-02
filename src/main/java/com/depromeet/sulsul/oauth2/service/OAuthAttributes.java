package com.depromeet.sulsul.oauth2.service;

import com.depromeet.sulsul.domain.member.dto.RoleType;
import com.depromeet.sulsul.domain.member.entity.Member;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

  public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
    switch (registrationId) {
      case KAKAO:
        return ofKakao("id", attributes);
      case NAVER:
        return ofNaver("id", attributes);
      default:
        throw new IllegalArgumentException();
    }
  }

  private static OAuthAttributes ofNaver(String id, Map<String, Object> attributes) {
    // TODO: 구현 예정
    return null;
  }

  private static OAuthAttributes ofKakao(String userNameAttributeName,
      Map<String, Object> attributes) {

    Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) response.get("profile");

    return OAuthAttributes.builder()
        .name((String) profile.get("nickname"))
        .email((String) response.get("email"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public Member toEntity() {
    return Member.builder()
        .name(name)
        .email(email)
        .role(RoleType.USER)
        .build();
  }
}
