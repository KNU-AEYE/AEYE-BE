package com.server.aeye.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.exception.ErrorStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(ErrorStatus.UNAUTHORIZED.getHttpStatusCode());
        response.setContentType("application/json;charset=UTF-8");
        String jsonResponse = new ObjectMapper().writeValueAsString(ApiResponseDto.error(ErrorStatus.UNAUTHORIZED, ErrorStatus.UNAUTHORIZED.getMessage()));
        response.getWriter().write(jsonResponse);
    }
}
