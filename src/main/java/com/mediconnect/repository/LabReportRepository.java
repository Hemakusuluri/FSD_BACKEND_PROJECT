package com.mediconnect.repository;

import com.mediconnect.entity.LabReport;
import com.mediconnect.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, Long> {
    List<LabReport> findByPatient(Patient patient);
    List<LabReport> findByPatientOrderByReportDateDesc(Patient patient);
}
