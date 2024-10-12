package com.demo.rbac.ecommerce.backend.services;

import com.demo.rbac.ecommerce.persistence.entitites.product.Product;
import com.demo.rbac.ecommerce.persistence.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
