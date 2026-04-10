package com.mediconnect.controller;

import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pharmacist")
public class PharmacistController {

    @Autowired private MedicationOrderRepository medicationOrderRepository;
    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private PharmacistRepository pharmacistRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<MedicationOrder>> getAllOrders() {
        return ResponseEntity.ok(medicationOrderRepository.findAll());
    }

    @GetMapping("/orders/pending")
    public ResponseEntity<List<MedicationOrder>> getPendingOrders() {
        return ResponseEntity.ok(medicationOrderRepository.findByStatus(MedicationOrder.Status.PENDING));
    }

    @PostMapping("/orders/create")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> body) {
        try {
            Prescription prescription = prescriptionRepository.findById(
                    Long.valueOf(body.get("prescriptionId").toString()))
                    .orElseThrow(() -> new RuntimeException("Prescription not found"));

            MedicationOrder order = MedicationOrder.builder()
                    .prescription(prescription)
                    .status(MedicationOrder.Status.PENDING)
                    .deliveryAddress(body.get("deliveryAddress") != null ? body.get("deliveryAddress").toString() : "")
                    .notes(body.get("notes") != null ? body.get("notes").toString() : "")
                    .build();

            return ResponseEntity.ok(medicationOrderRepository.save(order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        try {
            MedicationOrder order = medicationOrderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            order.setStatus(MedicationOrder.Status.valueOf(body.get("status").toString().toUpperCase()));
            if (body.get("totalAmount") != null) {
                order.setTotalAmount(new BigDecimal(body.get("totalAmount").toString()));
            }
            if (body.get("pharmacistId") != null) {
                pharmacistRepository.findById(Long.valueOf(body.get("pharmacistId").toString()))
                        .ifPresent(order::setPharmacist);
            }
            return ResponseEntity.ok(medicationOrderRepository.save(order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
