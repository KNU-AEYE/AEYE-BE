package com.server.aeye.DTO.member.response;

import com.server.aeye.domain.Member;
import com.server.aeye.enums.SocialLogin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private String name;
    private String profileUri;
    private SocialLogin socialLogin;

    public static MemberResponseDto toEntity(Member member){
        return MemberResponseDto.builder()
            .name(member.getName())
            .profileUri(member.getProfileUri())
            .socialLogin(member.getSocialLogin())
            .build();
    }
}
