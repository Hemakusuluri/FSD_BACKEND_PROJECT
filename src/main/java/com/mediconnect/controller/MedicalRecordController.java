package com.mediconnect.controller;

import com.mediconnect.entity.MedicalRecord;
import com.mediconnect.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/patient/{userId}")
    public ResponseEntity<List<MedicalRecord>> getPatientRecords(@PathVariable Long userId) {
        return ResponseEntity.ok(medicalRecordService.getPatientRecords(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalRecord>> getAllRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllRecords());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRecord(@RequestBody Map<String, Object> body) {
        try {
            return ResponseEntity.ok(medicalRecordService.createRecord(body));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
