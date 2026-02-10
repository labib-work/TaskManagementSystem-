package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.EmployeeRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.TaskRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.UserRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.TaskRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Employee;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Task;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.enums.TaskStatus;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions.ResourceNotFoundException;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public TaskResponse createTask(TaskRequestDto request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Task task = Task.builder()
                .taskTitle(request.getTaskTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .assignedEmployee(employee)
                .build();

       // log.info("Task created for employee: {}", employee.getEmployeeId());

        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequestDto request) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        task.setTaskTitle(request.getTaskTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getTasksForEmployee(String username) {

        Employee employee = getEmployeeFromUsername(username);

        return taskRepository.findByAssignedEmployee(employee)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> getTasksDueInNext3Days(String username) {

        Employee employee = getEmployeeFromUsername(username);

        LocalDate now = LocalDate.now();
        LocalDate threeDaysLater = now.plusDays(3);

        return taskRepository.findByAssignedEmployeeAndDueDateBetween(
                        employee, now, threeDaysLater)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TaskResponse updateTaskStatus(Long taskId, String status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        task.setStatus(TaskStatus.valueOf(status));

        return mapToResponse(taskRepository.save(task));
    }

    private Employee getEmployeeFromUsername(String username) {

        String email = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"))
                .getEmail();

        return employeeRepository.findAll()
                .stream()
                .filter(emp -> email.equals(emp.getEmail()))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not linked with user"));
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .taskTitle(task.getTaskTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .employeeName(task.getAssignedEmployee().getEmployeeName())
                .build();
    }
}
