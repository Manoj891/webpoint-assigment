package com.ms.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorMessage> handleException(CustomException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.toDto());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldError> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Field> field = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> field.add(Field.builder().message(fieldError.getDefaultMessage()).name(fieldError.getField()).build()));
        log.error(field.toString());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new FieldValidationException(FieldError.builder().message("validation error").field(field).build()).toField());
    }


    @ExceptionHandler(value = PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> permissionDeniedExceptionException(PermissionDeniedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.toDto());
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
    public ResponseEntity<ErrorMessage> handleException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(new CustomException(e.getCause().getMessage()).toDto());
    }


    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<ErrorMessage> handleException(InvalidDataAccessApiUsageException e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new CustomException(e.getCause().getMessage()).toDto());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorMessage> handleException(DataIntegrityViolationException e) {
        String message = "";
        try {
            message = Objects.requireNonNull(e.getRootCause()).getMessage();
            if (message.contains("index_contact_details_mobile_number"))
                message = "mobile number already exists";
        } catch (Exception ex) {
            message = e.getMessage();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomException(message).toDto());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException(e.getMessage()).toDto());
    }


}
