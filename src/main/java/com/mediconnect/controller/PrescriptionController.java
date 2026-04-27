package com.mediconnect.controller;

import com.mediconnect.entity.Prescription;
import com.mediconnect.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/patient/{userId}")
    public ResponseEntity<List<Prescription>> getPatientPrescriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(prescriptionService.getPatientPrescriptions(userId));
    }

    @GetMapping("/doctor/{userId}")
    public ResponseEntity<List<Prescription>> getDoctorPrescriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(prescriptionService.getDoctorPrescriptions(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Prescription>> getPendingPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getPendingPrescriptions());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPrescription(@RequestBody Map<String, Object> body) {
        try {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> medications = (List<Map<String, Object>>) body.get("medications");
            Prescription prescription = prescriptionService.createPrescription(
                Long.valueOf(body.get("appointmentId").toString()),
                body.get("diagnosis").toString(),
                body.get("instructions") != null ? body.get("instructions").toString() : "",
                medications
            );
            return ResponseEntity.ok(prescription);
        } catch (Exception e) {
            String errorMsg = e.getMessage() != null ? e.getMessage() : e.toString();
            return ResponseEntity.badRequest().body(Map.of("error", errorMsg));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(prescriptionService.updateStatus(id, body.get("status")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
