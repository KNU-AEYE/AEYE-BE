package com.server.aeye.controller;

import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.exception.SuccessStatus;
import com.server.aeye.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@SecurityRequirement(name = "Access Token")
@Tag(name = "EMAIL", description = "EMAIL API")
public class EmailController {

    private final EmailService emailService;

    // SendGrid API를 사용하여 이메일을 전송합니다.
    @Operation(summary = "이메일 전송", description = "SendGrid API를 사용하여 이메일을 전송합니다.")
    @PostMapping
    public ApiResponseDto<?> sendEmail(@Parameter(hidden = true) @AuthenticationPrincipal User user)
        throws IOException {
        emailService.sendEmail(user.getUsername());
        return ApiResponseDto.success(SuccessStatus.OK, SuccessStatus.OK.getMessage());
    }
}
