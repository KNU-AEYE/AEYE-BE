package com.server.aeye.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.server.aeye.domain.Report;
import com.server.aeye.infrastructure.ReportRepository;
import com.server.aeye.util.DataBucketUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final DataBucketUtil dataBucketUtil;

    public Report generateDailyReport() throws IOException, DocumentException {
        // 일일 관제일지 생성
        byte[] pdfData = createDailyReportPdf();

        String reportUri = dataBucketUtil.uploadDailyReport(pdfData, "application/pdf");
        Report report = Report.builder()
            .date(LocalDate.now())
            .reportUri(reportUri)
            .build();
        return reportRepository.save(report);
    }

    private byte[] createDailyReportPdf() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        document.add(new Paragraph("Daily Report"));

        // PDF 내용 추가

        document.close();

        return baos.toByteArray();
    }

}
