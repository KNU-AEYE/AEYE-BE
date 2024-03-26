package com.server.aeye.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.exception.ErrorStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(ErrorStatus.OAUTH_UNAUTHORIZED.getHttpStatusCode());
        response.setContentType("application/json;charset=UTF-8");
        String jsonResponse = new ObjectMapper().writeValueAsString(
            ApiResponseDto.error(ErrorStatus.OAUTH_UNAUTHORIZED, ErrorStatus.OAUTH_UNAUTHORIZED.getMessage()));
        response.getWriter().write(jsonResponse);
    }
}
