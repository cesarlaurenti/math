package com.tenpo.math.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIError {

    private LocalDateTime timestamp;
    private String message;

    public APIError(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
