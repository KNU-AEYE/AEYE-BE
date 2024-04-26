package com.server.aeye.infrastructure.querydsl.team;

import static com.server.aeye.domain.QTeam.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.domain.Team;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> searchTeam(String teamName) {
        return queryFactory
            .select(team)
            .from(team)
            .where(team.name.contains(teamName))
            .fetch();
    }
}
