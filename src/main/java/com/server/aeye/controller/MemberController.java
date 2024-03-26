package com.server.aeye.controller;

import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.DTO.member.request.MemberRequestDto;
import com.server.aeye.DTO.member.response.MemberDetailResponseDto;
import com.server.aeye.DTO.member.response.MemberResponseDto;
import com.server.aeye.exception.SuccessStatus;
import com.server.aeye.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@SecurityRequirement(name = "Access Token")
@Tag(name = "Member", description = "회원 관리 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    @GetMapping
    public ApiResponseDto<MemberResponseDto> getMember(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ApiResponseDto.success(SuccessStatus.OK, memberService.getMember(user.getUsername())
        );
    }

    @Operation(summary = "회원 정보 상세조회", description = "회원 정보를 상세조회합니다.")
    @GetMapping("/detail")
    public ApiResponseDto<MemberDetailResponseDto> getMemberDetail(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ApiResponseDto.success(SuccessStatus.OK, memberService.getMemberDetail(user.getUsername()));
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PutMapping("/update")
    public ApiResponseDto<?> updateMember(@Parameter(hidden = true) @AuthenticationPrincipal User user, @RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMember(user.getUsername(), memberRequestDto);
        return ApiResponseDto.success(SuccessStatus.OK, SuccessStatus.OK.getMessage());
    }

}
