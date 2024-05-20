package com.server.aeye.service;

import com.server.aeye.domain.Member;
import com.server.aeye.domain.Report;
import com.server.aeye.domain.VideoSummaryDocument;
import com.server.aeye.infrastructure.MemberRepository;
import com.server.aeye.infrastructure.VideoLogRepository;
import com.server.aeye.infrastructure.VideoRepository;
import com.server.aeye.infrastructure.VideoSummaryRepository;
import com.server.aeye.util.ThumbnailUtil;
import java.util.List;
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
    private final EmailService emailService;
    private final ReportService reportService;
    private final MemberRepository memberRepository;

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

    @Scheduled(cron = "0 0 8 * * *")
    public void generateDailyReportAndSendEmail() {
        // 1. 일단 일일 관제일지 생성 먼저
        Report report = reportService.generateDailyReport();

        // 2. 그 다음, 이메일 자동 발송 등록한 관제사들에게 발송
        List<Member> members = memberRepository.findBySubscribeDailyReportIsTrue();
        members.forEach(member -> {
            try {
                log.info("send email to {}", member.getName());
                log.info("send email to {}", member.getEmail());
                emailService.sendEmail(member, report);
            } catch (Exception e) {
                log.error("email send error", e);
            }
        });
    }
}
