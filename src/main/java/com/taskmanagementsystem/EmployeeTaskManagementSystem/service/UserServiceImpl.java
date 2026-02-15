package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.UserRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.CreateUserRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.User;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions.ResourceNotFoundException;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final Logger log =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public UserResponse createUser(CreateUserRequestDto request) {

       log.info("Creating user with username: {}", request.getUsername());

        if(request == null)
        {
            throw new ResourceNotFoundException("Your username,password,role and email cannot be null");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .email(request.getEmail())
                .active(true)
                .build();

        User saved = userRepository.save(user);

        log.info("User created successfully with id: {} and username: {}", saved.getId(), saved.getUsername());

        return mapToResponse(saved);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        log.info("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void activateDeactivateUser(Long userId, boolean active) {

        log.info("Attempting to {} user with id: {}", (active ? "activate" : "deactivate"), userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setActive(active);
        log.info("User with id: {} successfully {}", userId, (active ? "activated" : "deactivated"));
        userRepository.save(user);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .email(user.getEmail())
                .active(user.isActive())
                .build();
    }
}
