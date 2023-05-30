package com.ms.assignment.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    private String name;
    private String message;

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"message\":\"" + message + "\"}";
    }

}
