package com.mediconnect.repository;

import com.mediconnect.entity.Pharmacist;
import com.mediconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    Optional<Pharmacist> findByUser(User user);
    Optional<Pharmacist> findByUserId(Long userId);
}
