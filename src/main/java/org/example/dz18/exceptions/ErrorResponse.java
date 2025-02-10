package org.example.dz18.exceptions;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int statusCode;
}
