package com.taskmanagementsystem.EmployeeTaskManagementSystem.other;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.UserRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.User;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Role;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);



    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByUsername("admin")) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@gmail.com");
            admin.setRole(Role.ADMIN);
            admin.setActive(true);

            userRepository.save(admin);

            System.out.println("Default ADMIN inserted");

            log.info("Default ADMIN inserted");

        }
    }
}
