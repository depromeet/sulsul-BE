package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.common.dto.EnumModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BeerType implements EnumModel {
  //COMMENT: 임시 종류
  LIGHT_ALE("라이트 에일", "description of light ale",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyJAvcMkYUWruGNPzJzMxHDbs8ko50Ycz4VA&usqp=CAU"),
  IPA("인디아 페일 에일",
      "줄여서 IPA라고 많이 부른다. 18세기부터 등장한 영국 에일의 일종이다. 보통의 페일 에일보다 많은 양의 홉을 때려넣어서 강렬한 홉향이 나오는 게 특징이다.",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc5GxqbNiR_y_oBEhB03dAf1QS0Va944QD6g&usqp=CAU"),
  PALE_ALE("페일 에일",
      "효모가 맥주 위에 떠서 발효되는 맥주로 맥아와 홉의 사용량이 라거에 비해 많고 효모가 많이 살아있어 우리가 알고있는 라거류의 맥주보다 색이 진하며 향이 풍부하다. 다만, 어디까지나 라거에 비해서 색이 진하다는 것이지, 페일에일은 에일맥주 중에서도 색이 아주 밝은편에 속한다. 그리고 라거류가 보통 시원하게 먹는 것과 달리, 너무 차가우면 향을 느끼기 힘드므로 보다 높은 온도에서 덜 차갑게 먹게 된다.\n"
          +
          "\n" +
          "어느 페일에일이든, 기본적으로 비슷한 페일 몰트의 향은 가지고 있으나, 사용하는 홉의 종류나 첨가한 향, 그리고 홉과 맥아의 비율에 따라 맛과 향이 미묘하게 제각기 달라 후각에 민감한 사람들에게는 맥주메이커마다 맛이 판이하게 다르게 느껴진다. 홉의 종류에 따라 그리고 함량도 조금씩 다르기 때문에 흔히 알고있는 라거처럼 음료수같이 느껴지는 맥주가 있는가 하면 아메리칸 페일에일이나 인디안 페일에일의 경우 홉의 함량이 많아 맛이나 향이 진하거나, 쓴맛이 강하기도 하다.\n",
      "http://www.pubbronx.com/upload/125/20190726144726_a2846587.jpg"),
  BROWN_ALE("브라운 에일",
      "짙은 호박색부터 갈색까지 다양하다. 캐러멜과 초콜릿 맛이 뚜렷하다. 영국 북동부의 갈색 에일은 강하고, 종종 견과류가 많은 경향이 있는 반면, 영국 남부의 에일은 대개 더 진하고, 달콤하고, 알코올 함량이 낮습니다. 북미산 갈색 에일은 보통 영국산 에일보다 건조하며, 미국식 홉 품종으로 인해 감귤류 악센트와 아로마, 쓴맛, 중간 정도의 몸매를 가지고 있다. 에스터에서 나오는 과일은 가라앉는다. 차가운 온도로 차가워지면 약간의 흐릿함이 나타날 수 있습니다.",
      "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Oak_Creek_Nut_Brown_Ale_%2827911122885%29.jpg/1599px-Oak_Creek_Nut_Brown_Ale_%2827911122885%29.jpg?20180406183442"),
  LARGER("라거",
      "에일이 가볍고 복잡한 향이 나며 탄산이 적은 것에 비해, 라거는 약간의 보리향과 첨가되는 홉의 향을 제외하면 거의 향이 없다. 대신 가벼운 바디감과 강한 탄산감으로 청량감이 매우 빼어나며, 이 시원하고 깔끔함이 현대인의 취향에 어울려 맥주 생산의 절대 대다수를 차지하게 되었다. 또한 이 직선적인 스타일과 아주 최근에 유행하게 되었다는 역사적인 특징으로 에일에 비해 하위 분류가 적은 편이다.",
      "https://cdn.imweb.me/thumbnail/20211024/d9e69607bbc0b.jpg"),
  WEIZEN("바이젠",
      "밝은색을 띄는, 상쾌한 독일식 밀맥주입니다. 높은 탄산과, 깔끔한 끝맺음(dry finish), 부드러운 마우스필과 독특한 바나나와 클로브 같은 효모 특성이 있습니다. 청량감과 빠른 숙성이 특징인 바이스 비어는 낮은 홉 함량과 banana-and-clove 효모의 특징을 가졌습니다. 바이스 비어는 맛이 잘 변하지 않지만 제조 후 얼마 되지 않은 신선한 상태일 때 가장 맛있습니다. “mit hefe(효모 함유)” 버전은 효모가 남아있는 채로 마시고, “크리스탈” 버전은 깨끗하게 거른 후 음용합니다. 크리스탈 바이젠은 보통 mit hefe 바이스 비어보다 더 프루티하며 약품 냄새가 덜 나는 것으로 알려져 있습니다. 미국에서는 헤페바이젠이라고도 불립니다.\n",
      "https://contents.lotteon.com/itemimage/_v183701/LO/16/85/27/12/04/_1/68/52/71/20/5/LO1685271204_1685271205_1.jpg/dims/resizef/720X720"),
  PILSNER("필스너",
      "라거의 한 종류. 라거 특유의 투명한 황금빛과 시원한 청량감에 더해 사츠(Saaz) 홉의 쌉싸름한 맛과 풍미가 강조된 것이 특징. 일반 라거에 비해 더욱 강한 쓴맛과 깊은 풍미가 보리의 곡물향와 적절한 조화를 이루고, 미디엄 바디감이 특징이다.\n"
          +
          "\n" +
          "체코의 플젠(Plzeň) 지방에서 유래된 맥주. 기존의 맥주 제조법을 버리고 당시에도 맥주의 선진국이던 독일에서 새로 라거 제조법을 들여와 체코에서 생산한 맥주가 시초이다. 이 독일식 라거에 체코의 Zatec(독일어로 Saaz) 지방에서 생산된 홉을 보다 많이 사용하여, 라거보다 더 강하고 깊은 향과 쓴맛을 가지게 되었고, 이것이 필스너 스타일로 자리잡았다. 사츠 홉은 향이 허브와 흙의 중간적 성질을 가지며 깔끔한 쓴맛이 특징으로, 이 특징이 현재의 필스너 스타일을 만든 것이다.\n",
      "https://image.hmall.com/static/7/1/30/27/2127301710_0.jpg?RS=600x600&AR=0");

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
