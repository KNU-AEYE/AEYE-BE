package com.server.aeye.DTO.member.response;

import com.server.aeye.domain.Member;
import com.server.aeye.enums.SocialLogin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private Long id;
    private String name;
    private String profileUri;
    private SocialLogin socialLogin;

    public static MemberResponseDto toDto(Member member){
        return MemberResponseDto.builder()
            .id(member.getId())
            .name(member.getName())
            .profileUri(member.getProfileUri())
            .socialLogin(member.getSocialLogin())
            .build();
    }
}
