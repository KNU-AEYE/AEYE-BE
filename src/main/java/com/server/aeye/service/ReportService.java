package com.server.aeye.service;

import com.server.aeye.domain.Report;
import com.server.aeye.infrastructure.ReportRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public Report generateDailyReport() {
        // 일일 관제일지 생성
        Report report = Report.builder()
            .date(LocalDate.now())
            .reportUri("https://storage.googleapis.com/aeye-bucket/report/report_sample.pdf")
            .build();
        return reportRepository.save(report);
    }

}
