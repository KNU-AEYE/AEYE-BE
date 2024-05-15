package com.server.aeye.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.server.aeye.jwt.CustomAccessDeniedHandler;
import com.server.aeye.jwt.CustomEntryPoint;
import com.server.aeye.jwt.JwtFilter;
import com.server.aeye.jwt.TokenProvider;
import com.server.aeye.oauth.OAuth2LoginFailureHandler;
import com.server.aeye.oauth.OAuth2LoginSuccessHandler;
import com.server.aeye.oauth.OAuth2UserService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private String keyFileLocation;

    @Bean
    public Storage storage() throws IOException {
        InputStream keyFile = ResourceUtils.getURL(keyFileLocation).openStream();
        return StorageOptions.newBuilder()
            .setCredentials(GoogleCredentials.fromStream(keyFile))
            .build()
            .getService();
    }

    private static final String[] WHITE_LIST = {
        "/api/auth/**",
        "/swagger-ui/**",
        "/api-docs/**",
        "/login",
        "/video/cloud"
    };

    private static final String[] AUTHENTICATION_LIST = {
        "/api/v1/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .headers(c -> c.frameOptions(FrameOptionsConfig::disable).disable())
            .oauth2Login(oauth2Login -> oauth2Login
                .successHandler(oAuth2LoginSuccessHandler) // OAuth2 로그인 성공 핸들러
                .failureHandler(oAuth2LoginFailureHandler) // OAuth2 로그인 실패 핸들러
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                    .userService(oAuth2UserService) // 사용자 정보를 처리하는 서비스
                )
            )
            .authorizeHttpRequests(auth -> {
                auth
                    .requestMatchers(WHITE_LIST).permitAll()
                    .requestMatchers(AUTHENTICATION_LIST).hasRole("USER")
                    .anyRequest().authenticated();
            }).exceptionHandling(c ->
                c.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDeniedHandler)
            ).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
