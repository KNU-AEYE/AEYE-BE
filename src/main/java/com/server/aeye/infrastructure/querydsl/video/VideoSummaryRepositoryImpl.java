package com.server.aeye.infrastructure.querydsl.video;

import static com.server.aeye.domain.QVideoSummary.videoSummary;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.domain.VideoSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoSummaryRepositoryImpl implements VideoSummaryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<VideoSummary> searchVideoSummary(String keyword) {
        return queryFactory
            .select(videoSummary)
            .from(videoSummary)
            .where(videoSummary.content.contains(keyword))
            .fetch();
    }
}
