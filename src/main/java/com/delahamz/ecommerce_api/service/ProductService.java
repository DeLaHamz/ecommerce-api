package com.delahamz.ecommerce_api.service;

import com.delahamz.ecommerce_api.entity.Product;
import com.delahamz.ecommerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //collect list of all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //collect a product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found by id:"+id));
    }

    // create or save a product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // delete a product by id
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Impossible to delete product with id:"+id+" because it doesn't exist");
        }
        productRepository.deleteById(id);
    }

}