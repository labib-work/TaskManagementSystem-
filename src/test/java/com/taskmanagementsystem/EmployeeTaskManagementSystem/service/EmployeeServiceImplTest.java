package com.taskmanagementsystem.EmployeeTaskManagementSystem.service;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.EmployeeRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository.TaskRepository;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Employee;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .employeeName("Snigdha Shoily")
                .employeeId("EMP001")
                .build();
    }

    @Test
    void deleteEmployee_ShouldDeleteTasksAndEmployee_WhenEmployeeExists() {

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(taskRepository, times(1))
                .deleteByAssignedEmployeeId(1L);

        verify(employeeRepository, times(1))
                .delete(employee);
    }

    @Test
    void deleteEmployee_ShouldThrowException_WhenEmployeeNotFound() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                employeeService.deleteEmployee(1L)
        );

        verify(taskRepository, never())
                .deleteByAssignedEmployeeId(anyLong());

        verify(employeeRepository, never())
                .delete(any());
    }
}
