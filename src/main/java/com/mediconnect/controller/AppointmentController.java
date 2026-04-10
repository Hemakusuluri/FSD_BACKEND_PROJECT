package com.mediconnect.controller;

import com.mediconnect.entity.Appointment;
import com.mediconnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/patient/{userId}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getPatientAppointments(userId));
    }

    @GetMapping("/doctor/{userId}")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointments(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody Map<String, Object> body) {
        try {
            Appointment appointment = appointmentService.bookAppointment(
                Long.valueOf(body.get("patientUserId").toString()),
                Long.valueOf(body.get("doctorId").toString()),
                body.get("date").toString(),
                body.get("time").toString(),
                body.get("symptoms") != null ? body.get("symptoms").toString() : "",
                body.get("type") != null ? body.get("type").toString() : "VIRTUAL"
            );
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Appointment appointment = appointmentService.updateStatus(id, body.get("status"));
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(appointmentService.getStats());
    }
}
