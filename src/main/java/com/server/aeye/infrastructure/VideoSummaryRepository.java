package com.server.aeye.infrastructure;

import com.server.aeye.domain.VideoSummary;
import com.server.aeye.infrastructure.querydsl.video.VideoSummaryRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSummaryRepository extends JpaRepository<VideoSummary, Long>,
    VideoSummaryRepositoryCustom {

    List<VideoSummary> findAllByIsCheckedFalse();
}
