package com.delahamz.ecommerce_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDTO(
    @NotBlank(message = "Le nom du produit est obligatoire")
    String name,

    String description,

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit etre strictement supérieur à zero")
    BigDecimal price,

    @NotNull(message = "La quantité en stock est obligatoire")
    @PositiveOrZero(message = "La quantité en stock ne peut pas être négative")
    Integer stockQuantity
) {
}