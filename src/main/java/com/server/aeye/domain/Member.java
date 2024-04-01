package com.server.aeye.domain;

import com.server.aeye.enums.Authority;
import com.server.aeye.enums.SocialLogin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String profileUri;

    private String phone;

    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    private SocialLogin socialLogin;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private LocalDateTime lastActive;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Member(String email, String name, String profileUri, String oauth2Id, SocialLogin socialLogin) {
        this.email = email;
        this.name = name;
        this.profileUri = profileUri;
        this.oauth2Id = oauth2Id;
        this.socialLogin = socialLogin;
        this.authority = Authority.ROLE_USER;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
}
