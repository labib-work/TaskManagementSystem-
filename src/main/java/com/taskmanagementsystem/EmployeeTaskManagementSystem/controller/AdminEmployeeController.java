package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.EmployeeRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.EmployeeResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Admin Employee APIs", description = "Operations related to admin employees")
@RestController
@RequestMapping("/admin/employees")
public class AdminEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create an employee")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequestDto request) {

        EmployeeResponse data = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @Operation(summary = "Update an employee")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto request) {

         EmployeeResponse data = employeeService.updateEmployee(id, request);

         return ResponseEntity.ok(data);
    }

    @Operation(summary = "Delete an employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<EmployeeResponse> datas = employeeService.getAllEmployees();

        return ResponseEntity.ok(datas);
    }

    @Operation(summary = "Get an employee by id")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {

        EmployeeResponse data = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(data);
    }
}
