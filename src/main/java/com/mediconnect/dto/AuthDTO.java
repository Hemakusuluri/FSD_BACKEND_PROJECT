package com.mediconnect.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AuthDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        private String fullName;
        private String email;
        private String password;
        private String phone;
        private String role; // DOCTOR, PATIENT, PHARMACIST
        // Doctor fields
        private String specialization;
        private String licenseNumber;
        private Integer experienceYears;
        private Double consultationFee;
        // Patient fields
        private String dateOfBirth;
        private String gender;
        private String bloodGroup;
        // Pharmacist fields
        private String pharmacyName;
        private String pharmacyAddress;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private String role;
        private Long userId;
        private String fullName;
        private String email;
    }
}
