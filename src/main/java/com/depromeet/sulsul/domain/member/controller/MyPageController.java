package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/my-page")
public class MyPageController {
    private final MemberFacade memberFacade;

    @GetMapping("/{id}")
    public ResponseDto<MyPageRequestDto> findMyPageByMemberId(@PathVariable Long id) {
        return ResponseDto.from(memberFacade.findMyPageByMemberId(id));
    }
}
