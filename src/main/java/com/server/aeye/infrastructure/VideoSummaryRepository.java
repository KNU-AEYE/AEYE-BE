package com.server.aeye.infrastructure;

import com.server.aeye.domain.VideoSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSummaryRepository extends JpaRepository<VideoSummary, Long> {

}
