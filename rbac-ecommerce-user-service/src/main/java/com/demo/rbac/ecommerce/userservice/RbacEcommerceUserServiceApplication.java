package com.demo.rbac.ecommerce.userservice;

import com.demo.rbac.ecommerce.userservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@EnableMethodSecurity
@EntityScan(basePackages = "com.demo.rbac.ecommerce.persistence")
@EnableJpaRepositories(basePackages = "com.demo.rbac.ecommerce.persistence")
@RequiredArgsConstructor
public class RbacEcommerceUserServiceApplication implements CommandLineRunner {
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(RbacEcommerceUserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productService.getDataFromProductCatalog();
    }
}
