package com.delahamz.ecommerce_api.service;

import com.delahamz.ecommerce_api.dto.ProductRequestDTO;
import com.delahamz.ecommerce_api.dto.ProductResponseDTO;
import com.delahamz.ecommerce_api.entity.Product;
import com.delahamz.ecommerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.delahamz.ecommerce_api.exception.ResourceNotFoundException;// ajouter à la suite du remplacement de RunTimeException par ResourceNotFoundException

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //collect list of all products
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    //collect a product by id
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found by id:" + id));
        return mapToResponseDTO(product);
    }

    // create or save a product
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.name());
        product.setDescription(requestDTO.description());
        product.setPrice(requestDTO.price());
        product.setStockQuantity(requestDTO.stockQuantity());

        Product savedProduct = productRepository.save(product);
        return mapToResponseDTO(savedProduct);
    }

    // delete a product by id
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Impossible to delete product with id:"+id+" because it doesn't exist");
        }
        productRepository.deleteById(id);
    }

    // methode to map Product entity to ProductResponseDTO
    private ProductResponseDTO mapToResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }
}