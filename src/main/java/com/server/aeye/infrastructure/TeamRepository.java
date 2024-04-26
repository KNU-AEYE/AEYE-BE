package com.server.aeye.infrastructure;

import com.server.aeye.domain.Team;
import com.server.aeye.infrastructure.querydsl.team.TeamRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {

}
