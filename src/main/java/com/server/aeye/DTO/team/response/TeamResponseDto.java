package com.server.aeye.DTO.team.response;

import com.server.aeye.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamResponseDto {

    private Long id;
    private String name;
    private String description;

    public static TeamResponseDto toDto(Team team) {
        return TeamResponseDto.builder()
            .id(team.getId())
            .name(team.getName())
            .description(team.getDescription())
            .build();
    }
}
