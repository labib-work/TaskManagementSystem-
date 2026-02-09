package com.taskmanagementsystem.EmployeeTaskManagementSystem.response;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Role;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private Role role;
    private String email;
    private boolean active;
}
