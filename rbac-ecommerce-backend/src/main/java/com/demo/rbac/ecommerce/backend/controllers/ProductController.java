package com.demo.rbac.ecommerce.backend.controllers;

import com.demo.rbac.ecommerce.backend.services.ProductService;
import com.demo.rbac.ecommerce.persistence.entitites.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController extends GenericExceptionHandler {
    private final ProductService productService;

    @PreAuthorize("hasRole(T(com.demo.rbac.ecommerce.persistence.enums.Role).ROLE_BUYER.name())")
    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
