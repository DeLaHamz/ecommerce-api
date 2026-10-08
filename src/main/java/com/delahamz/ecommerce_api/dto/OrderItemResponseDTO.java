package com.delahamz.ecommerce_api.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subTotal
) {
    
}