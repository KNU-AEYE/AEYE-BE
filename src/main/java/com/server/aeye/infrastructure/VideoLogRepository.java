package com.server.aeye.infrastructure;

import com.server.aeye.domain.VideoLog;
import com.server.aeye.infrastructure.querydsl.video.VideoLogRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLogRepository extends JpaRepository<VideoLog, Long>,
    VideoLogRepositoryCustom {

}
