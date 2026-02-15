package com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException (String mesage){
        super(mesage);
    }
}
