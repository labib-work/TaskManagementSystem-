package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/tasks")
public class EmployeeTaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getMyTasks(Authentication authentication) {

        List<TaskResponse> datas = taskService.getTasksForEmployee(authentication.getName());

        return ResponseEntity.ok(datas);
    }

    @GetMapping("/due-soon")
    public ResponseEntity<List<TaskResponse>> getTasksDueSoon(Authentication authentication) {
        List<TaskResponse> datas = taskService.getTasksDueInNext3Days(authentication.getName());

        return ResponseEntity.ok(datas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        TaskResponse data = taskService.updateTaskStatus(id, status);

        return ResponseEntity.ok(data);
    }
}
