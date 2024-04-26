package com.server.aeye.controller;

import com.server.aeye.DTO.ApiResponseDto;
import com.server.aeye.DTO.team.request.TeamRequestDto;
import com.server.aeye.DTO.team.response.TeamResponseDto;
import com.server.aeye.exception.SuccessStatus;
import com.server.aeye.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@SecurityRequirement(name = "Access Token")
@Tag(name = "Team", description = "팀 관리 API")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "팀 검색", description = "팀 이름으로 팀을 검색합니다.")
    @GetMapping("/search/{teamName}")
    public ApiResponseDto<List<TeamResponseDto>> searchTeam(@PathVariable String teamName,
        @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return ApiResponseDto.success(SuccessStatus.TEAM_SEARCH_SUCCESS, teamService.searchTeam(teamName));
    }

    @Operation(summary = "팀 생성", description = "팀을 생성합니다.")
    @PostMapping
    public ApiResponseDto<?> createTeam(@RequestBody TeamRequestDto teamRequestDto,
        @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        teamService.createTeam(teamRequestDto, user.getUsername());
        return ApiResponseDto.success(SuccessStatus.CREATE_TEAM_SUCCESS, SuccessStatus.CREATE_TEAM_SUCCESS.getMessage());
    }

}
