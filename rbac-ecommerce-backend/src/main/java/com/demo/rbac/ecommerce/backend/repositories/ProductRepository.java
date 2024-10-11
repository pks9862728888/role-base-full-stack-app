package com.demo.rbac.ecommerce.backend.repositories;

import com.demo.rbac.ecommerce.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
