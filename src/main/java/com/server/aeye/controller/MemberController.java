package com.server.aeye.controller;

import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.DTO.member.request.MemberNameRequestDto;
import com.server.aeye.DTO.member.request.MemberPhoneRequestDto;
import com.server.aeye.DTO.member.response.MemberDetailResponseDto;
import com.server.aeye.DTO.member.response.MemberResponseDto;
import com.server.aeye.exception.SuccessStatus;
import com.server.aeye.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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
        return ApiResponseDto.success(SuccessStatus.GET_MEMBER_SUCCESS, memberService.getMember(user.getUsername())
        );
    }

    @Operation(summary = "회원 정보 상세조회", description = "회원 정보를 상세조회합니다.")
    @GetMapping("/detail")
    public ApiResponseDto<MemberDetailResponseDto> getMemberDetail(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ApiResponseDto.success(SuccessStatus.GET_MEMBER_DETAIL_SUCCESS, memberService.getMemberDetail(user.getUsername()));
    }

    @Operation(summary = "회원 이름 수정", description = "회원 이름을 수정합니다.")
    @PutMapping("/update/name")
    public ApiResponseDto<?> updateName(@Parameter(hidden = true) @AuthenticationPrincipal User user,
        @Valid @RequestBody MemberNameRequestDto nameRequestDto) {
        memberService.updateName(user.getUsername(), nameRequestDto);
        return ApiResponseDto.success(SuccessStatus.NO_CONTENT, SuccessStatus.NO_CONTENT.getMessage());
    }

    @Operation(summary = "회원 전화번호 추가", description = "회원 전화번호를 추가합니다.")
    @PutMapping("/update/phone")
    public ApiResponseDto<?> updatePhone(@Parameter(hidden = true) @AuthenticationPrincipal User user,
        @Valid @RequestBody MemberPhoneRequestDto phoneRequestDto) {
        memberService.updatePhone(user.getUsername(), phoneRequestDto);
        return ApiResponseDto.success(SuccessStatus.NO_CONTENT, SuccessStatus.NO_CONTENT.getMessage());
    }

    @Operation(summary = "온라인 관리자 조회", description = "현재 접속중인 관리자를 표시합니다.")
    @GetMapping("/online")
    public ApiResponseDto<List<MemberResponseDto>> getOnlineAdmin(@Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ApiResponseDto.success(SuccessStatus.GET_ONLINE_ADMIN_SUCCESS, memberService.getOnlineAdmin(user.getUsername()));
    }

}
