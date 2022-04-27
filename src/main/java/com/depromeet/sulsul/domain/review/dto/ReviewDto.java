package com.depromeet.sulsul.domain.review.dto;


import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private MemberDto memberDto;
    private BeerDto beerDto;
    private String content;

    public ReviewDto(Long id, String content, MemberDto memberDto, BeerDto beerDto) {
        this.id = id;
        this.content = content;
        this.memberDto = memberDto;
        this.beerDto = beerDto;
    }
}
