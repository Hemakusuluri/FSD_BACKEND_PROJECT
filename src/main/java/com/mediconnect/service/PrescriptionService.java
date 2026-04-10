package com.mediconnect.service;

import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PrescriptionService {

    @Autowired private PrescriptionRepository prescriptionRepository;
    @Autowired private AppointmentRepository appointmentRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    public List<Prescription> getPatientPrescriptions(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return prescriptionRepository.findByPatientOrderByCreatedAtDesc(patient);
    }

    public List<Prescription> getDoctorPrescriptions(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return prescriptionRepository.findByDoctor(doctor);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> getPendingPrescriptions() {
        return prescriptionRepository.findByStatus(Prescription.Status.PENDING);
    }

    @Transactional
    public Prescription createPrescription(Long appointmentId, String diagnosis, String instructions, List<Map<String, Object>> medications) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Prescription prescription = Prescription.builder()
                .appointment(appointment)
                .patient(appointment.getPatient())
                .doctor(appointment.getDoctor())
                .diagnosis(diagnosis)
                .instructions(instructions)
                .status(Prescription.Status.PENDING)
                .build();

        prescription = prescriptionRepository.save(prescription);

        if (medications != null) {
            Prescription finalPrescription = prescription;
            List<PrescriptionMedication> meds = medications.stream().map(m -> {
                PrescriptionMedication med = new PrescriptionMedication();
                med.setPrescription(finalPrescription);
                med.setMedicationName(m.get("medicationName").toString());
                med.setDosage(m.get("dosage").toString());
                med.setFrequency(m.get("frequency").toString());
                med.setDuration(m.get("duration").toString());
                med.setQuantity(m.get("quantity") != null ? Integer.valueOf(m.get("quantity").toString()) : null);
                med.setInstructions(m.get("instructions") != null ? m.get("instructions").toString() : null);
                return med;
            }).toList();
            prescription.setMedications(meds);
        }

        // Update appointment status to completed
        appointment.setStatus(Appointment.Status.COMPLETED);
        appointmentRepository.save(appointment);

        return prescriptionRepository.save(prescription);
    }

    @Transactional
    public Prescription updateStatus(Long prescriptionId, String status) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        prescription.setStatus(Prescription.Status.valueOf(status.toUpperCase()));
        return prescriptionRepository.save(prescription);
    }
}
