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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
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
        log.info("onAuthenticationSuccess, oauth2Id: " + id);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        log.info("tokenDto: " + tokenDto);
        response.sendRedirect(redirectUri + "?accessToken=" + tokenDto.getAccessToken());
    }
}
