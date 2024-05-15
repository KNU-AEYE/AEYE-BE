package com.server.aeye.service;

import com.server.aeye.domain.VideoSummaryDocument;
import com.server.aeye.infrastructure.VideoLogRepository;
import com.server.aeye.infrastructure.VideoRepository;
import com.server.aeye.infrastructure.VideoSummaryRepository;
import com.server.aeye.util.ThumbnailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final VideoRepository videoRepository;
    private final VideoService videoService;
    private final VideoSummaryRepository videoSummaryRepository;
    private final VideoLogRepository videoLogRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final ThumbnailUtil thumbnailUtil;

    // 5분에 한 번 씩
    @Scheduled(cron = "0 */5 * * * *")
    public void synchronizeElastic() {
        log.info("elasticsearch synchronize start");
        videoSummaryRepository.findAllByIsCheckedFalse().forEach(videoSummary -> {
            VideoSummaryDocument videoSummaryDocument = VideoSummaryDocument.of(videoSummary);
            elasticsearchOperations.save(videoSummaryDocument);
            videoSummary.check();
            videoSummaryRepository.save(videoSummary);
        });
    }

    // 1시간에 한 번 씩
//    @Scheduled(cron = "5 * * * * *")
//    public void generateThumbnail() {
//        log.info("generate thumbnail start");
//        videoLogRepository.findByThumbnailUriIsNull().forEach(thumbnailUtil::generateThumbnail);
//    }
}
