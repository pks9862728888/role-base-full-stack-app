package com.demo.rbac.ecommerce.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AppRbacEcommerceEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(AppRbacEcommerceEurekaServer.class, args);
    }
}
