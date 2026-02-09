package com.taskmanagementsystem.EmployeeTaskManagementSystem.dto;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.Priority;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.TaskStatus;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TaskRequestDto {
    @NotBlank
    private String taskTitle;

    private String description;

    @NotNull
    private Priority priority;

    @NotNull
    private TaskStatus status;

    @Future
    private LocalDate dueDate;

    @NotNull
    private Long employeeId;
}
