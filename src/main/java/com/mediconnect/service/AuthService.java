package com.mediconnect.service;

import com.mediconnect.dto.AuthDTO;
import com.mediconnect.entity.*;
import com.mediconnect.repository.*;
import com.mediconnect.security.CustomUserDetailsService;
import com.mediconnect.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private PharmacistRepository pharmacistRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private CustomUserDetailsService userDetailsService;

    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails, user.getRole().name());

        return new AuthDTO.LoginResponse(token, user.getRole().name(), user.getId(), user.getFullName(), user.getEmail());
    }

    @Transactional
    public AuthDTO.LoginResponse register(AuthDTO.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User.Role role = User.Role.valueOf(request.getRole().toUpperCase());

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .isActive(true)
                .build();
        user = userRepository.save(user);

        switch (role) {
            case DOCTOR -> {
                Doctor doctor = Doctor.builder()
                        .user(user)
                        .specialization(request.getSpecialization() != null ? request.getSpecialization() : "General")
                        .licenseNumber(request.getLicenseNumber() != null ? request.getLicenseNumber() : "LIC-" + System.currentTimeMillis())
                        .experienceYears(request.getExperienceYears())
                        .consultationFee(request.getConsultationFee() != null ? BigDecimal.valueOf(request.getConsultationFee()) : BigDecimal.valueOf(500))
                        .build();
                doctorRepository.save(doctor);
            }
            case PATIENT -> {
                Patient patient = Patient.builder()
                        .user(user)
                        .dateOfBirth(request.getDateOfBirth() != null ? LocalDate.parse(request.getDateOfBirth()) : null)
                        .gender(request.getGender() != null ? Patient.Gender.valueOf(request.getGender().toUpperCase()) : null)
                        .bloodGroup(request.getBloodGroup())
                        .build();
                patientRepository.save(patient);
            }
            case PHARMACIST -> {
                Pharmacist pharmacist = Pharmacist.builder()
                        .user(user)
                        .licenseNumber("PHARM-" + System.currentTimeMillis())
                        .pharmacyName(request.getPharmacyName())
                        .pharmacyAddress(request.getPharmacyAddress())
                        .build();
                pharmacistRepository.save(pharmacist);
            }
            default -> {}
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails, role.name());
        return new AuthDTO.LoginResponse(token, role.name(), user.getId(), user.getFullName(), user.getEmail());
    }
}
