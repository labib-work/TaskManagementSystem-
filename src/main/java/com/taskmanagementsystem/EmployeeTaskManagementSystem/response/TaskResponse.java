package com.taskmanagementsystem.EmployeeTaskManagementSystem.response;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Priority;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {

    private Long id;
    private String taskTitle;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate dueDate;
    private String employeeName;
}
