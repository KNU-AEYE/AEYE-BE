package com.server.aeye.oauth;

import com.server.aeye.domain.Member;
import com.server.aeye.infrastructure.MemberRepository;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            // 카카오 예외처리
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            email = (String) kakaoAccount.get("email");
        }

        log.info("email: " + email);

        Member member = getMember(attributes, email);

        return new CustomOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(member.getAuthority().name())),
            oAuth2User.getAttributes(),
            attributes.getNameAttributeKey(),
            member.getAuthority(),
            attributes.getOAuth2UserInfo().getId()
        );
    }

    private Member getMember(OAuthAttributes attributes, String email) {
        Member member = memberRepository.findByOauth2Id(attributes.getOAuth2UserInfo().getId()).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            log.info("authentication != null");
            return saveMember(attributes, email);
        }

        if (member == null) {
            log.info("member == null");
            return saveMember(attributes);
        }
        log.info("member != null");
        return member;
    }

    private Member saveMember(OAuthAttributes attributes) {
        log.info("멤버가 존재하지 않기 때문에 멤버를 생성합니다.");
        Member member = attributes.toEntity(attributes.getOAuth2UserInfo());
        return memberRepository.save(member);
    }

    private Member saveMember(OAuthAttributes attributes, String email) {
        log.info("멤버가 존재하기 때문에 멤버를 업데이트합니다.");
        Member member = memberRepository.getMemberByEmail(email);
        member.changeOauth2Id(attributes.getOAuth2UserInfo().getId());
        return memberRepository.save(member);
    }
}
