package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.CreateUserRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.UserResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User APIs" , description = "Operations related to users")
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a user")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequestDto request) {

        UserResponse data = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<UserResponse> data = userService.getAllUsers();

        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Activate or deactivate a user")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> activateDeactivateUser(
            @PathVariable Long id,
            @RequestParam boolean active) {

        userService.activateDeactivateUser(id, active);

        return ResponseEntity.noContent().build();
    }
}
