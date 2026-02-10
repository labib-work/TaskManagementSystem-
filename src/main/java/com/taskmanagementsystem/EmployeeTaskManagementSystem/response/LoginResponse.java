package com.taskmanagementsystem.EmployeeTaskManagementSystem.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String token;
}
