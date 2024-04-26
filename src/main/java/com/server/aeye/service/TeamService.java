package com.server.aeye.service;

import com.server.aeye.DTO.team.request.TeamRequestDto;
import com.server.aeye.DTO.team.response.TeamResponseDto;
import com.server.aeye.domain.Team;
import com.server.aeye.infrastructure.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void createTeam(TeamRequestDto teamRequestDto, String username) {
        Team team = Team.builder()
            .name(teamRequestDto.getName())
            .description(teamRequestDto.getDescription())
            .build();
        teamRepository.save(team);
    }

    public List<TeamResponseDto> searchTeam(String teamName) {
        List<Team> teamList = teamRepository.searchTeam(teamName);
        return teamList.stream()
            .map(TeamResponseDto::toDto)
            .toList();
    }

}
