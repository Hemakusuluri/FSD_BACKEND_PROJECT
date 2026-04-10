package com.mediconnect.service;

import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LabReportService {

    @Autowired private LabReportRepository labReportRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    public List<LabReport> getPatientReports(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return labReportRepository.findByPatientOrderByReportDateDesc(patient);
    }

    public List<LabReport> getAllReports() {
        return labReportRepository.findAll();
    }

    @Transactional
    public LabReport createReport(Map<String, Object> body) {
        Patient patient = patientRepository.findById(Long.valueOf(body.get("patientId").toString()))
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        LabReport report = LabReport.builder()
                .patient(patient)
                .reportName(body.get("reportName").toString())
                .reportType(body.get("reportType") != null ? body.get("reportType").toString() : "General")
                .resultSummary(body.get("resultSummary") != null ? body.get("resultSummary").toString() : "")
                .reportDate(body.get("reportDate") != null ? LocalDate.parse(body.get("reportDate").toString()) : LocalDate.now())
                .isNormal(body.get("isNormal") != null ? Boolean.valueOf(body.get("isNormal").toString()) : null)
                .build();

        if (body.get("doctorId") != null) {
            doctorRepository.findById(Long.valueOf(body.get("doctorId").toString()))
                    .ifPresent(report::setDoctor);
        }

        return labReportRepository.save(report);
    }
}
