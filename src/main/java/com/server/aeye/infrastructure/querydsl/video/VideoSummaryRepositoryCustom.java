package com.server.aeye.infrastructure.querydsl.video;

import com.server.aeye.domain.VideoSummary;
import java.util.List;

public interface VideoSummaryRepositoryCustom {

    List<VideoSummary> searchVideoSummary(String keyword);

}
