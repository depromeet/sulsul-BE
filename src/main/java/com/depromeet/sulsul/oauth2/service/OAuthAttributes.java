package com.depromeet.sulsul.oauth2.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private static final String KAKAO = "kakao";
    private static final String NAVER = "naver";

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;

    }

    public static  OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        switch (registrationId){
            case KAKAO:
                return ofKakao("id",attributes);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String,Object>) attributes.get("kakao_account");

        kakaoAccount.forEach((k,v) -> System.out.println("k = " + k + " v " + v));

        return OAuthAttributes.builder()
                .email((String) kakaoAccount.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
}
