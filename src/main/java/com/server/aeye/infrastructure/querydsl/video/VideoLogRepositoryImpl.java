package com.server.aeye.infrastructure.querydsl.video;

import static com.server.aeye.domain.QVideoLog.videoLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.domain.VideoLog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoLogRepositoryImpl implements VideoLogRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<VideoLog> searchVideoLog(String keyword, PageRequest pageRequest) {
        List<VideoLog> videoLogs =  queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(videoLog.content.contains(keyword))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        Long count = getCount(keyword);
        return new PageImpl<>(videoLogs, pageRequest, count);
    }

    private Long getCount(String keyword) {
        return queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(videoLog.content.contains(keyword))
            .fetchCount();
    }
}
