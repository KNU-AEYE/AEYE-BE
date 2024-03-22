package com.server.aeye.oauth.provider;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private final String name;
    private final String email;
    private final String picture;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        this.name = (String) profile.get("nickname");
        this.email = (String) kakaoAccount.get("email");
        this.picture = (String) profile.get("profile_image_url");
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPicture() {
        return this.picture;
    }
}
