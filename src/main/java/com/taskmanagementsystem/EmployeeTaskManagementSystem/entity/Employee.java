package com.taskmanagementsystem.EmployeeTaskManagementSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Builder
@Data
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeName;

    @Column(unique = true, nullable = false)
    private String employeeId;

    private String department;

    private String designation;

    @Column(unique = true)
    private String email;

    private String phone;
}
