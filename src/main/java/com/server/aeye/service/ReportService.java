package com.server.aeye.service;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.server.aeye.domain.Report;
import com.server.aeye.infrastructure.ReportRepository;
import com.server.aeye.util.DataBucketUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final DataBucketUtil dataBucketUtil;

    @Transactional
    public Report generateDailyReport() throws IOException {

        // 이미 있으면 있는 거 리턴
        if (reportRepository.findByDate(LocalDate.now()).isPresent()) {
            return reportRepository.findByDate(LocalDate.now()).get();
        }

        // 일일 관제일지 생성
        byte[] pdfData = createDailyReportPdf();

        String reportUri = dataBucketUtil.uploadDailyReport(pdfData, "application/pdf");
        Report report = Report.builder()
            .date(LocalDate.now())
            .reportUri(reportUri)
            .build();
        return reportRepository.save(report);
    }

    private byte[] createDailyReportPdf() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 리소스 경로에서 PDF 템플릿 파일 열기
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("report_template.pdf");
        if (resourceStream == null) {
            throw new IOException("report_template.pdf not found in resources");
        }

        // PDF 템플릿 파일 열기
        PdfReader reader = new PdfReader(resourceStream);
        log.info("reader: {}", reader);
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        // AcroForm을 통해 폼 필드를 가져옴
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        Map<String, PdfFormField> fields = form.getAllFormFields();
        log.info("fields: {}", fields);

        // 폼 필드에 데이터 입력
        setFieldValue(fields, "title", "일일 관제일지");
        setFieldValue(fields, "date", LocalDate.now().toString());
        setFieldValue(fields, "time1", "08:00");
        setFieldValue(fields, "event1", "System Check");
        setFieldValue(fields, "description1", "All systems operational.");
        setFieldValue(fields, "time2", "12:00");
        setFieldValue(fields, "event2", "Update");
        setFieldValue(fields, "description2", "Software updated to version 1.0.1.");
        setFieldValue(fields, "time3", "16:00");
        setFieldValue(fields, "event3", "Maintenance");
        setFieldValue(fields, "description3", "Performed routine maintenance.");
        setFieldValue(fields, "time4", "20:00");
        setFieldValue(fields, "event4", "Backup");
        setFieldValue(fields, "description4", "Data backed up to secure server.");


        // 폼을 읽기 전용으로 만듦
        form.flattenFields();

        pdfDoc.close();
        return baos.toByteArray();
    }

    private void setFieldValue(Map<String, PdfFormField> fields, String fieldName, String value) {
        PdfFormField field = fields.get(fieldName);
        if (field != null) {
            field.setValue(value);
        } else {
            log.error("Field {} does not exist in the PDF template.", fieldName);
        }
    }
}
