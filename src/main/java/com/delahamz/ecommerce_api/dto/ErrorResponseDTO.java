package com.delahamz.ecommerce_api.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
    int status,
    String error,
    String message,
    Map<String, String> validationErrors,
    LocalDateTime timestamp
) {
    
}