package com.ms.assignment.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldValidationException extends RuntimeException {
    private final FieldError field;

    public FieldValidationException(FieldError field) {
        this.field = field;
    }

    public FieldError toField() {
        return field;
    }
}
