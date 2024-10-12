package com.demo.rbac.ecommerce.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@EnableMethodSecurity
public class AppRbacEcommerceBackend {

    public static void main(String[] args) {
        SpringApplication.run(AppRbacEcommerceBackend.class, args);
    }
}
