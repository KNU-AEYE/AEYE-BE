package com.server.aeye.infrastructure.querydsl.team;

import com.server.aeye.domain.Team;
import java.util.List;

public interface TeamRepositoryCustom {

    List<Team> searchTeam(String teamName);

}
