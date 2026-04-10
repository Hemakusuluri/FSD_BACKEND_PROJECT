package com.mediconnect.repository;

import com.mediconnect.entity.MedicationOrder;
import com.mediconnect.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {
    List<MedicationOrder> findByStatus(MedicationOrder.Status status);
    List<MedicationOrder> findByPharmacist(Pharmacist pharmacist);
    List<MedicationOrder> findByOrderByOrderedAtDesc();
}
