package com.taskmanagementsystem.EmployeeTaskManagementSystem.response;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private Role role;
    private String email;
    private boolean active;
}
