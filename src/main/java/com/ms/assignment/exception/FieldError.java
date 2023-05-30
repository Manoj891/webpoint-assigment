package com.ms.assignment.exception;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldError {
    private String message;
    private List<Field> field;


}
