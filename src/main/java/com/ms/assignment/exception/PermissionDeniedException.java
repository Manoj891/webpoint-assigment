package com.ms.assignment.exception;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {
        super(null, null, false, false);
    }

    public ErrorMessage toDto() {
        return dto;
    }

    private final ErrorMessage dto = ErrorMessage.builder().message("You do not have permission to access this feature.").build();

}
