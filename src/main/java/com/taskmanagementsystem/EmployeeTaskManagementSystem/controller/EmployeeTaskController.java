package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/tasks")
public class EmployeeTaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponse> getMyTasks(Authentication authentication) {
        return taskService.getTasksForEmployee(authentication.getName());
    }

    @GetMapping("/due-soon")
    public List<TaskResponse> getTasksDueSoon(Authentication authentication) {
        return taskService.getTasksDueInNext3Days(authentication.getName());
    }

    @PatchMapping("/{id}/status")
    public TaskResponse updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return taskService.updateTaskStatus(id, status);
    }
}
