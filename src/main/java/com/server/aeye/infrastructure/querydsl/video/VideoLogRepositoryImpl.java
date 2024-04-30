package com.server.aeye.infrastructure.querydsl.video;

import static com.server.aeye.domain.QVideoLog.videoLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.domain.VideoLog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoLogRepositoryImpl implements VideoLogRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<VideoLog> searchVideoLog(String videoName) {
        return queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(videoLog.content.contains(videoName))
            .fetch();
    }
}
