package com.delahamz.ecommerce_api.controller;

import com.delahamz.ecommerce_api.dto.ProductRequestDTO;
import com.delahamz.ecommerce_api.dto.ProductResponseDTO;
import com.delahamz.ecommerce_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductService productService;

    //GET /api/products : collect all products
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());//200 http's code ok
    }

    //GET /api/products/{id} : collect a product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //POST /api/products : create a new product
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO createProduct = productService.createProduct(requestDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);//201 http's code created
    }

    //DELETE /api/products/{id} : delete a product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); //204 http's code no content
    }

}