package com.taskmanagementsystem.EmployeeTaskManagementSystem.Repository;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Employee;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByAssignedEmployee(Employee employee);

    List<Task> findByAssignedEmployeeAndDueDateBetween(
            Employee employee,
            LocalDate start,
            LocalDate end
    );

    List<Task> findByAssignedEmployeeId(Long employeeId);

    void deleteByAssignedEmployeeId(Long employeeId);

    boolean existsByAssignedEmployeeId(Long employeeId);

}
