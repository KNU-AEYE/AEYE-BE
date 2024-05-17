package com.server.aeye.infrastructure.querydsl.video;

import com.server.aeye.DTO.video.request.VideoSearchRequestDto;
import com.server.aeye.domain.VideoLog;
import com.server.aeye.domain.VideoSummary;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface VideoLogRepositoryCustom {

    //v1
    Page<VideoLog> searchVideoLog1(String keyword, PageRequest pageRequest);

    //v2
    Page<VideoLog> searchVideoLog2(VideoSearchRequestDto videoSearchRequestDto, PageRequest pageRequest);

    Page<VideoSummary> searchVideoSummary(String keyword, PageRequest pageRequest);

}
