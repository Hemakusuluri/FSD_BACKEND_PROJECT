package com.mediconnect.controller;

import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import com.mediconnect.service.LabReportService;
import com.mediconnect.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired private DoctorRepository doctorRepository;
    @Autowired private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getDoctorByUserId(@PathVariable Long userId) {
        return doctorRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            if (body.get("specialization") != null) doctor.setSpecialization(body.get("specialization").toString());
            if (body.get("bio") != null) doctor.setBio(body.get("bio").toString());
            if (body.get("consultationFee") != null) doctor.setConsultationFee(new java.math.BigDecimal(body.get("consultationFee").toString()));
            if (body.get("availableDays") != null) doctor.setAvailableDays(body.get("availableDays").toString());
            return ResponseEntity.ok(doctorRepository.save(doctor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
