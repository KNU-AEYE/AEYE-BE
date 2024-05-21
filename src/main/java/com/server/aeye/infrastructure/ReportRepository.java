package com.server.aeye.infrastructure;

import com.server.aeye.domain.Report;
import com.server.aeye.exception.ErrorStatus;
import com.server.aeye.exception.model.CustomException;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByDate(LocalDate date);

    default Report getReportByDate(LocalDate date) {
        return findByDate(date).orElseThrow(
            () -> new CustomException(ErrorStatus.REPORT_NOT_FOUND, ErrorStatus.REPORT_NOT_FOUND.getMessage())
        );
    }
}
