package com.depromeet.sulsul.domain.review.dto;


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
    private String content;

    public ReviewDto(Long id, String content, MemberDto memberDto) {
        this.id = id;
        this.content = content;
        this.memberDto = memberDto;
    }
}
