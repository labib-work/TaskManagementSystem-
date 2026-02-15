package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Task APIs" , description = "Operations related to employee tasks")
@RestController
@RequestMapping("/employee/tasks")
public class EmployeeTaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get Employee Tasks")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getMyTasks(Authentication authentication) {

        List<TaskResponse> datas = taskService.getTasksForEmployee(authentication.getName());

        return ResponseEntity.ok(datas);
    }

    @Operation(summary = "Get list of task that is Due in next 3 Days")
    @GetMapping("/due-soon")
    public ResponseEntity<List<TaskResponse>> getTasksDueSoon(Authentication authentication) {
        List<TaskResponse> datas = taskService.getTasksDueInNext3Days(authentication.getName());

        return ResponseEntity.ok(datas);
    }

    @Operation(summary = "Update the task status")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        TaskResponse data = taskService.updateTaskStatus(id, status);

        return ResponseEntity.ok(data);
    }
}
