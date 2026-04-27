package com.mediconnect;

import com.mediconnect.entity.User;
import com.mediconnect.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateAdmin implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User admin = userRepository.findByEmail("admin@mediconnect.com").orElse(null);
        if (admin != null) {
            System.out.println("Updating admin password...");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            userRepository.save(admin);
            System.out.println("Admin password updated successfully to 'Admin@123'");
        } else {
            System.out.println("Admin user not found, inserting...");
            admin = new User();
            admin.setFullName("System Admin");
            admin.setEmail("admin@mediconnect.com");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setRole(User.Role.ADMIN);
            admin.setPhone("9999999999");
            admin.setIsActive(true);
            userRepository.save(admin);
            System.out.println("Admin user created successfully.");
        }
    }
}
