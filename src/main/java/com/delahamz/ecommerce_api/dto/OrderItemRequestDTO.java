package com.delahamz.ecommerce_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDTO(
        @NotNull(message = "L'id du produit est obligatoire")
        Long productId,

        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité commandée doit être supérieure à zéro")
        Integer quantity
) {
    
}