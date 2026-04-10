package com.mediconnect.repository;

import com.mediconnect.entity.Prescription;
import com.mediconnect.entity.Patient;
import com.mediconnect.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatient(Patient patient);
    List<Prescription> findByDoctor(Doctor doctor);
    List<Prescription> findByStatus(Prescription.Status status);
    List<Prescription> findByPatientOrderByCreatedAtDesc(Patient patient);
}
