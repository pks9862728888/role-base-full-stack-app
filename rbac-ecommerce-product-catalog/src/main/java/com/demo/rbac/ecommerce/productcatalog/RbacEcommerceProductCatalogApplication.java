package com.demo.rbac.ecommerce.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RbacEcommerceProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbacEcommerceProductCatalogApplication.class, args);
    }

}
