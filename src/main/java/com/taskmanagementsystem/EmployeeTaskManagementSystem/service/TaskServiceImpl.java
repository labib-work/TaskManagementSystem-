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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);


    @Override
    public TaskResponse createTask(TaskRequestDto request) {

        log.info("Attempting to create task for employeeId: {}", request.getEmployeeId());

        if(checkRequest(request)){
            throw new IllegalArgumentException("Task title, priority, employee id and status cannot be null");
        }

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", request.getEmployeeId());
                    return new ResourceNotFoundException("Employee not found");
                });

        Task task = Task.builder()
                .taskTitle(request.getTaskTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .assignedEmployee(employee)
                .build();

        log.info("Task created successfully with id: {} for employeeId: {}", task.getId(), employee.getId());


        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequestDto request) {

        log.info("Attempting to update task with id: {}", taskId);

        if(checkRequest(request)){
            log.error("Failed to update task {}: Task title, priority, employeeId or status is null", taskId);
            throw new IllegalArgumentException("Task title, priority, employee id and status cannot be null");
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    log.error("Task not found with id: {}", taskId);
                    return new ResourceNotFoundException("Task not found");
                });

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> {
                    log.error("Employee not found for updating with id: {}", request.getEmployeeId());
                    return new ResourceNotFoundException("Employee not found");
                });

        task.setTaskTitle(request.getTaskTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setAssignedEmployee(employee);

        log.info("Task updated successfully with id: {} for employeeId: {}", task.getId(), employee.getId());

        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getTasksForEmployee(String username) {

        log.info("Fetching tasks for user: {}", username);

        Employee employee = getEmployeeFromUsername(username);

        return taskRepository.findByAssignedEmployee(employee)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> getTasksDueInNext3Days(String username) {

        log.info("Fetching tasks due in next 3 days for user: {}", username);

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

        log.info("Updating status of taskId {} to {}", taskId, status);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    log.error("Task not found for update with id: {}", taskId);
                    return new ResourceNotFoundException("Task not found");
                });

        task.setStatus(TaskStatus.valueOf(status));

        log.info("Task status updated successfully for taskId {} to {}", taskId, status);

        return mapToResponse(taskRepository.save(task));
    }



    private Employee getEmployeeFromUsername(String username) {

        log.info("Fetching employee linked to user: {}", username);

        String email = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new ResourceNotFoundException("User not found");
                })
                .getEmail();

        return employeeRepository.findAll()
                .stream()
                .filter(emp -> email.equals(emp.getEmail()))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("No employee linked with user: {}", username);
                    return new ResourceNotFoundException("Employee not linked with user");
                });
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

    private boolean checkRequest(TaskRequestDto request){

        return request.getEmployeeId() == null || request.getPriority() == null ||
                request.getTaskTitle() == null || request.getStatus() == null;
    }
}
