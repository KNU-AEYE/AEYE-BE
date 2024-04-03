package com.server.aeye.controller;

import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.DTO.video.VideoListResponseDto;
import com.server.aeye.DTO.video.VideoResponseDto;
import com.server.aeye.exception.SuccessStatus;
import com.server.aeye.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@SecurityRequirement(name = "Access Token")
@Tag(name = "Video", description = "영상 관리 API")
public class VideoController {

    private final VideoService videoService;

    @Operation(summary = "영상 목록 조회", description = "영상 목록을 조회합니다.")
    @GetMapping
    public ApiResponseDto<VideoListResponseDto> getVideoList(
        @Parameter(hidden = true) @AuthenticationPrincipal User user,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponseDto.success(SuccessStatus.GET_VIDEO_SUCCESS,
            videoService.getVideoList(user.getUsername(), PageRequest.of(page, size)));
    }

    @Operation(summary = "영상 정보 조회", description = "영상 정보를 조회합니다.")
    @GetMapping("/detail/{videoId}")
    public ApiResponseDto<VideoResponseDto> getVideo(
        @Parameter(hidden = true) @AuthenticationPrincipal User user, @PathVariable Long videoId) {
        return ApiResponseDto.success(
            SuccessStatus.GET_VIDEO_SUCCESS, videoService.getVideo(user.getUsername(), videoId));
    }

}
