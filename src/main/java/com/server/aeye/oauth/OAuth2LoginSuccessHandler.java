package com.server.aeye.oauth;

import com.server.aeye.DTO.auth.TokenDto;
import com.server.aeye.enums.Authority;
import com.server.aeye.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Value("${spring.security.oauth2.client.redirect-uri}")
    private String redirectUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String id = customOAuth2User.getOauth2Id();

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        response.sendRedirect(redirectUri + "?accessToken=" + tokenDto.getAccessToken());
    }
}
