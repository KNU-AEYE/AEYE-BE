package com.server.aeye.infrastructure.querydsl.video;

import com.server.aeye.domain.VideoLog;
import com.server.aeye.domain.VideoSummary;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface VideoLogRepositoryCustom {


    Page<VideoLog> searchVideoLog(String keyword, PageRequest pageRequest);

    Page<VideoSummary> searchVideoSummary(String keyword, PageRequest pageRequest);

}
