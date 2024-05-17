package com.server.aeye.infrastructure.querydsl.video;

import static com.server.aeye.domain.QVideoLog.videoLog;
import static com.server.aeye.domain.QVideoSummary.videoSummary;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.aeye.DTO.video.request.VideoSearchRequestDto;
import com.server.aeye.domain.VideoLog;
import com.server.aeye.domain.VideoSummary;
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
    public Page<VideoLog> searchVideoLog1(String keyword, PageRequest pageRequest) {
        List<VideoLog> videoLogs =  queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(videoLog.content.contains(keyword))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        Long count = getCount1(keyword);
        return new PageImpl<>(videoLogs, pageRequest, count);
    }

    @Override
    public Page<VideoLog> searchVideoLog2(VideoSearchRequestDto videoSearchRequestDto, PageRequest pageRequest) {
        List<VideoLog> videoLogs =  queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(
                videoSearchRequestDto.getKeyword() != null && !videoSearchRequestDto.getKeyword().isEmpty()
                    ? videoLog.content.contains(videoSearchRequestDto.getKeyword())
                    : null,
                videoSearchRequestDto.getCity() != null && !videoSearchRequestDto.getCity().isEmpty()
                    ? videoLog.video.city.contains(videoSearchRequestDto.getCity())
                    : null,
                videoSearchRequestDto.getDistrict() != null && !videoSearchRequestDto.getDistrict().isEmpty()
                    ? videoLog.video.district.contains(videoSearchRequestDto.getDistrict())
                    : null,
                videoSearchRequestDto.getTag() != null && !videoSearchRequestDto.getTag().isEmpty()
                    ? videoLog.tag.contains(videoSearchRequestDto.getTag())
                    : null
            )
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();


        Long count = getCount2(videoSearchRequestDto);
        return new PageImpl<>(videoLogs, pageRequest, count);
    }

    @Override
    public Page<VideoSummary> searchVideoSummary(String keyword, PageRequest pageRequest) {
        List<VideoSummary> videoSummaries = queryFactory
            .select(videoSummary)
            .from(videoSummary)
            .where(videoSummary.content.contains(keyword))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(videoSummary)
            .from(videoSummary)
            .where(videoSummary.content.contains(keyword))
            .fetchCount();
        return new PageImpl<>(videoSummaries, pageRequest, count);

    }

    private Long getCount1(String keyword) {
        return queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(videoLog.content.contains(keyword))
            .fetchCount();
    }

    private Long getCount2(VideoSearchRequestDto videoSearchRequestDto) {
        return queryFactory
            .select(videoLog)
            .from(videoLog)
            .where(
                videoSearchRequestDto.getKeyword() != null && !videoSearchRequestDto.getKeyword().isEmpty()
                    ? videoLog.content.contains(videoSearchRequestDto.getKeyword())
                    : null,
                videoSearchRequestDto.getCity() != null && !videoSearchRequestDto.getCity().isEmpty()
                    ? videoLog.video.city.contains(videoSearchRequestDto.getCity())
                    : null,
                videoSearchRequestDto.getDistrict() != null && !videoSearchRequestDto.getDistrict().isEmpty()
                    ? videoLog.video.district.contains(videoSearchRequestDto.getDistrict())
                    : null,
                videoSearchRequestDto.getTag() != null && !videoSearchRequestDto.getTag().isEmpty()
                    ? videoLog.tag.contains(videoSearchRequestDto.getTag())
                    : null
            )
            .fetchCount();
    }
}
