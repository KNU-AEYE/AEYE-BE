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
    private String phone;
    private SocialLogin socialLogin;
    private boolean isAdmin;
    private boolean subscribeDailyReport;

    public static MemberDetailResponseDto toDto(Member member) {
        return MemberDetailResponseDto.builder()
            .id(member.getId())
            .name(member.getName())
            .email(member.getEmail())
            .profileUri(member.getProfileUri())
            .oauth2Id(member.getOauth2Id())
            .phone(member.getPhone())
            .socialLogin(member.getSocialLogin())
            .isAdmin(member.getAuthority() == Authority.ROLE_ADMIN)
            .subscribeDailyReport(member.isSubscribeDailyReport())
            .build();
    }
}
