package com.server.aeye.oauth;

import com.server.aeye.domain.Member;
import com.server.aeye.oauth.provider.GoogleOAuth2UserInfo;
import com.server.aeye.oauth.provider.KakaoOAuth2UserInfo;
import com.server.aeye.oauth.provider.OAuth2UserInfo;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

    private final String nameAttributeKey;
    private final OAuth2UserInfo oAuth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        if ("google".equals(registrationId)) {
            return google(userNameAttributeName, attributes);
        }
        return kakao(userNameAttributeName, attributes);
    }

    private static OAuthAttributes google(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .nameAttributeKey(userNameAttributeName)
            .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
            .build();
    }

    private static OAuthAttributes kakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .nameAttributeKey(userNameAttributeName)
            .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
            .build();
    }

    public Member toEntity(OAuth2UserInfo oAuth2UserInfo) {
        return Member.builder()
            .name(oAuth2UserInfo.getName())
            .email(oAuth2UserInfo.getEmail())
            .profileUri(oAuth2UserInfo.getPicture())
            .oauth2Id(oAuth2UserInfo.getId())
            .build();
    }
}
