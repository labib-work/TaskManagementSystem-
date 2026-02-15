package com.taskmanagementsystem.EmployeeTaskManagementSystem.exceptions;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponseDto> handleResponse(ResourceNotFoundException ex)
//    {
//        ErrorResponseDto error = new ErrorResponseDto(
//                LocalDateTime.now(),
//                HttpStatus.NOT_FOUND.value(),
//                ex.getMessage()
//        );
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(error);
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
//    public ResponseEntity<ErrorResponseDto> handleBadCredentials(Exception ex) {
//
//        ErrorResponseDto error = new ErrorResponseDto(
//                LocalDateTime.now(),
//                HttpStatus.UNAUTHORIZED.value(),
//                "Invalid username or password"
//        );
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(error);
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorResponseDto> handleAccessDenied(AccessDeniedException ex) {
//
//        ErrorResponseDto error = new ErrorResponseDto(
//                LocalDateTime.now(),
//                HttpStatus.FORBIDDEN.value(),
//                "You are not authorized to access this resource"
//        );
//
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(error);
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDto> handleGeneric(Exception ex) {
//
//        ErrorResponseDto error = new ErrorResponseDto(
//                LocalDateTime.now(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Something went wrong. Please try again later."
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(error);
//    }
//
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponseDto> handleBadRequest(IllegalArgumentException ex) {
//        ErrorResponseDto error = new ErrorResponseDto(
//                LocalDateTime.now(),
//                HttpStatus.BAD_REQUEST.value(),
//                ex.getMessage()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(error);
//    }
}

