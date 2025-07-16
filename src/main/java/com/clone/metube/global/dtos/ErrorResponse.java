package com.clone.metube.global.dtos;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
}