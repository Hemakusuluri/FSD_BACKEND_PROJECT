package com.mediconnect.controller;

import com.mediconnect.entity.LabReport;
import com.mediconnect.service.LabReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lab-reports")
public class LabReportController {

    @Autowired
    private LabReportService labReportService;

    @GetMapping("/patient/{userId}")
    public ResponseEntity<List<LabReport>> getPatientReports(@PathVariable Long userId) {
        return ResponseEntity.ok(labReportService.getPatientReports(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LabReport>> getAllReports() {
        return ResponseEntity.ok(labReportService.getAllReports());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReport(@RequestBody Map<String, Object> body) {
        try {
            return ResponseEntity.ok(labReportService.createReport(body));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
