package com.ms.assignment.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException {
    private final ErrorMessage dto;


    public CustomException(String message) {
        log.info(message);
        dto = ErrorMessage.builder().message(message).build();
    }

    public ErrorMessage toDto() {
        return dto;
    }

}
