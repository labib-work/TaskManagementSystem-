package com.taskmanagementsystem.EmployeeTaskManagementSystem.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeResponse {

    private Long id;
    private String employeeName;
    private String employeeId;
    private String department;
    private String designation;
    private String email;
    private String phone;
}
