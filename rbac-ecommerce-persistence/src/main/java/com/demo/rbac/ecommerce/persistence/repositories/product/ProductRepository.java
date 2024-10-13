package com.demo.rbac.ecommerce.persistence.repositories.product;

import com.demo.rbac.ecommerce.persistence.entitites.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
