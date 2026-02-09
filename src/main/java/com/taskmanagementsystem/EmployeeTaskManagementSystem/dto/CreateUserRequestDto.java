package com.taskmanagementsystem.EmployeeTaskManagementSystem.dto;


import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    @Email
    @NotBlank
    private String email;


}
