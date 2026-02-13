package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;


import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.TaskRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/tasks")
public class AdminTaskController {

    @Autowired
    private TaskService taskService;



    @PostMapping
    public ResponseEntity<TaskResponse> createAndAssignTask(
            @Valid @RequestBody TaskRequestDto request) {

        TaskResponse data = taskService.createTask(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request) {

        TaskResponse data = taskService.updateTask(id, request);

        return ResponseEntity.ok(data);
    }
}
