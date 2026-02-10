package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.TaskRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequestDto request);

    TaskResponse updateTask(Long taskId, TaskRequestDto request);

    List<TaskResponse> getTasksForEmployee(String username);

    List<TaskResponse> getTasksDueInNext3Days(String username);

    TaskResponse updateTaskStatus(Long taskId, String status);
}
