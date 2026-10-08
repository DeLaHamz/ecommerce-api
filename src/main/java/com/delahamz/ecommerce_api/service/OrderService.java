package com.delahamz.ecommerce_api.service;

import com.delahamz.ecommerce_api.dto.OrderItemRequestDTO;
import com.delahamz.ecommerce_api.dto.OrderItemResponseDTO;
import com.delahamz.ecommerce_api.dto.OrderRequestDTO;
import com.delahamz.ecommerce_api.dto.OrderResponseDTO;
import com.delahamz.ecommerce_api.entity.Order;
import com.delahamz.ecommerce_api.entity.OrderItem;
import com.delahamz.ecommerce_api.entity.Product;
import com.delahamz.ecommerce_api.exception.ResourceNotFoundException;
import com.delahamz.ecommerce_api.repository.OrderRepository;
import com.delahamz.ecommerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDTO createOrder (OrderRequestDTO requestDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDTO : requestDTO.items()) {
            // 1-Recuperation du produit
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id : " + itemDTO.productId()));
        
            // 2-Verification disponibilité en stock
            if (product.getStockQuantity() < itemDTO.quantity()) {
                throw new IllegalStateException("Stock insuffisant pour le produit : " + product.getName()
                    + " (Demandé : " + itemDTO.quantity() + ", Disponible : " + product.getStockQuantity() + ")");
            }

            //3- Decrementation 'atomique' du stock
            product.setStockQuantity(product.getStockQuantity() - itemDTO.quantity());
            productRepository.save(product);

            //4- Calcul du prix total pour l'article/le produit
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDTO.quantity()));
            totalAmount = totalAmount.add(itemTotal);

            //5- Creation de l'article de la commande avec snapshot du prix unitaire
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setUnitPrice(product.getPrice());

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        //Sauvegarder en cascade de la commande et de ses articles
        Order savedOrder = orderRepository.save(order);

        return mapToResponseDTO(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'id : " + id));
        return mapToResponseDTO(order);
    }

    public OrderResponseDTO mapToResponseDTO(Order order) {
        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                ))
                .toList();

        return new OrderResponseDTO(
            order.getId(),
            order.getOrderDate(),
            order.getTotalAmount(),
            itemDTOs
        );
    }
}