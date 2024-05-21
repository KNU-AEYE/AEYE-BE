package com.server.aeye.service;

import com.server.aeye.domain.Member;
import com.server.aeye.domain.Report;
import com.server.aeye.infrastructure.MemberRepository;
import com.server.aeye.infrastructure.ReportRepository;
import com.server.aeye.util.SendGridUtil;
import java.io.IOException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;
    private final ReportService reportService;
    private final SendGridUtil sendGridUtil;

    public void sendEmail() throws IOException {
        sendGridUtil.sendEmail();
    }

    // 수동으로 버튼 눌렀을 때
    public void sendEmail(String username) throws IOException {
        Member member = memberRepository.getMemberByOauth2Id(username);
        Report report = reportService.generateDailyReport();
        sendGridUtil.sendDynamicTemplateEmailv2(member, report);
    }

    // 관제일지 구독 시
    public void sendEmail(Member member, Report report) throws IOException {
        sendGridUtil.sendDynamicTemplateEmailv2(member, report);
    }
}
