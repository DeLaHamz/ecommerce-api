package com.delahamz.ecommerce_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequestDTO(
        @NotEmpty(message = "La commande doit contenir au moins un article")
        @Valid
        List<OrderItemRequestDTO> items
) {
    
}