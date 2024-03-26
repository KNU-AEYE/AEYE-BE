package com.server.aeye.DTO.member.response;

import com.server.aeye.domain.Member;
import com.server.aeye.enums.Authority;
import com.server.aeye.enums.SocialLogin;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDetailResponseDto {

    private Long id;
    private String name;
    private String email;
    private String profileUri;
    private String oauth2Id;
    private SocialLogin socialLogin;
    private boolean isAdmin;

    public static MemberDetailResponseDto toEntity(Member member) {
        return MemberDetailResponseDto.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .profileUri(member.getProfileUri())
            .oauth2Id(member.getOauth2Id())
            .socialLogin(member.getSocialLogin())
            .isAdmin(member.getAuthority() == Authority.ROLE_ADMIN)
            .build();
    }
}
