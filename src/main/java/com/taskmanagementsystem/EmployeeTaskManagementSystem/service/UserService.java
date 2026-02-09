package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.CreateUserRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequestDto request);

    List<UserResponse> getAllUsers();

    void activateDeactivateUser(Long userId, boolean active);
}
