package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.EmployeeRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.EmployeeRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Employee;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions.ResourceNotFoundException;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequestDto request) {

        // log.info("Creating employee with ID: {}", request.getEmployeeId());

        Employee employee = Employee.builder()
                .employeeName(request.getEmployeeName())
                .employeeId(request.getEmployeeId())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequestDto request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employee.setEmployeeName(request.getEmployeeName());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());

        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return mapToResponse(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .employeeName(employee.getEmployeeName())
                .employeeId(employee.getEmployeeId())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .build();
    }
}
