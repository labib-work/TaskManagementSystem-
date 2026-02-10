package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;


import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.TaskRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/tasks")
public class AdminTaskController {

    @Autowired
    private TaskService taskService;

    public AdminTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse createTask(
            @Valid @RequestBody TaskRequestDto request) {
        return taskService.createTask(request);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request) {
        return taskService.updateTask(id, request);
    }
}
