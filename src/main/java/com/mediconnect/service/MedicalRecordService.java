package com.mediconnect.service;

import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MedicalRecordService {

    @Autowired private MedicalRecordRepository medicalRecordRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private AppointmentRepository appointmentRepository;

    public List<MedicalRecord> getPatientRecords(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return medicalRecordRepository.findByPatientOrderByRecordDateDesc(patient);
    }

    public List<MedicalRecord> getAllRecords() {
        return medicalRecordRepository.findAll();
    }

    @Transactional
    public MedicalRecord createRecord(Map<String, Object> body) {
        Patient patient = patientRepository.findById(Long.valueOf(body.get("patientId").toString()))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findByUserId(Long.valueOf(body.get("doctorUserId").toString()))
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        MedicalRecord record = MedicalRecord.builder()
                .patient(patient)
                .doctor(doctor)
                .diagnosis(body.get("diagnosis").toString())
                .treatment(body.get("treatment") != null ? body.get("treatment").toString() : "")
                .notes(body.get("notes") != null ? body.get("notes").toString() : "")
                .build();

        if (body.get("appointmentId") != null) {
            appointmentRepository.findById(Long.valueOf(body.get("appointmentId").toString()))
                    .ifPresent(record::setAppointment);
        }

        return medicalRecordRepository.save(record);
    }
}
