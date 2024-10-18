package com.demo.rbac.ecommerce.userservice.services;

import com.demo.rbac.ecommerce.persistence.entitites.product.Product;
import com.demo.rbac.ecommerce.persistence.repositories.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAllReturnsAllProducts() {
        List<Product> allProducts = List.of(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(allProducts);
        List<Product> products = productService.findAll();
        Assertions.assertEquals(allProducts, products);
        verify(productRepository, times(1)).findAll();
    }
}
