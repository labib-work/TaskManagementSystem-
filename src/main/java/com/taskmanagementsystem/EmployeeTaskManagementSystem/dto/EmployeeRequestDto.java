package com.taskmanagementsystem.EmployeeTaskManagementSystem.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmployeeRequestDto {

    @NotBlank
    private String employeeName;

    @NotBlank
    private String employeeId;

    private String department;

    private String designation;

    @Email
    private String email;

    private String phone;
}
