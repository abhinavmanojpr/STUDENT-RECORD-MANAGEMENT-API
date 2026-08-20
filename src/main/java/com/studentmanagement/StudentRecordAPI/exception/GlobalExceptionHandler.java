package com.studentmanagement.StudentRecordAPI.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponse response = new ErrorResponse(
                400,
                "Validation failed",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(
            StudentNotFoundException exception) {

        ErrorResponse response = new ErrorResponse(
            404,
            exception.getMessage(),
            null
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFound(
            CourseNotFoundException exception) {

        ErrorResponse response = new ErrorResponse(
            404,
            exception.getMessage(),
            null
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEnrollmentNotFound(
            EnrollmentNotFoundException exception) {

        ErrorResponse response = new ErrorResponse(
            404,
            exception.getMessage(),
            null
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlResponseEntity(
        EmailAlreadyExistsException exception){

            ErrorResponse response =new ErrorResponse(
                409,
                exception.getMessage(),
                null
            );

            return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
            );
        }
}