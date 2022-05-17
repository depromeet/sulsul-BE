package com.depromeet.sulsul.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRecordDto {
    private static final long serialVersionUID = -3240908099376045282L;

    private Long id;
    private String name;
}
