package com.server.aeye.infrastructure.querydsl.video;

import com.server.aeye.domain.VideoLog;
import java.util.List;

public interface VideoLogRepositoryCustom {

    List<VideoLog> searchVideoLog(String keyword);

}
