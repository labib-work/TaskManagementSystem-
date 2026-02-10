package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.EmployeeRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequestDto request);

    EmployeeResponse updateEmployee(Long id, EmployeeRequestDto request);

    void deleteEmployee(Long id);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);
}
