package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.common.dto.EnumModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BeerType implements EnumModel {

  ALE("에일","쓴 맛이 적고 단 맛이 나며, 풀 바디감이 느껴지는 목 넘김이 깔끔한 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/ale.png"),
  PALE_ALE("페일 에일","붉은 빛과 맑고 밝은 빛을 띄며, 꽃향기와 같은 풍부한 향이 느껴지는 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/pale_ale.png"),
  IPA("IPA","탄산이 비교적 약하고 강렬한 홉향과 강하고 텁텁한 쓴 맛이 나는 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/ipa.png"),
  LAGER("라거","황금빛의 풍부한 탄산과 청량감을 가진 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/lager.png"),
  PILSNER("필스너","밝고 투명한 노란빛으로, 단 맛과 쓴 맛이 어우러진 깔끔한 맛의 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/pilsner.png"),
  WHEAT_BEER("밀맥주","밀 맥아의 비율이 50% 이상으로, 뿌옇고 엷은 빛깔의 부드러운 밀맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/wheat_beer.png"),
  STOUT("스타우트","포터보다 더 짙은 검은색을 띄며, 탄 맛과 쓴 맛도 더 강한 흑맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/stout.png"),
  PORTER("포터","도수가 높고 비교적 달콤한 맛이 나며, 짙고 그윽한 향이 나는 흑맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/porter.png"),
  RADLER("라들러","도수가 낮고 라거 맥주를 베이스로 음료수를 블렌딩해 탄산과 단 맛이 강한 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/radler.png"),
  LOW_MALT_BEER("발포주","맥아 함량이 매우 적은 유사 맥주로, 먹기 편한 가벼운 맛의 맥주","https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/BEER/TYPE/low_malt_beer.png");

  private final String korean;
  private final String description;
  private final String imageUrl;

  @Override
  public String getKey() {
    return name();
  }

  @Override
  public String getValue() {
    return korean;
  }
}
