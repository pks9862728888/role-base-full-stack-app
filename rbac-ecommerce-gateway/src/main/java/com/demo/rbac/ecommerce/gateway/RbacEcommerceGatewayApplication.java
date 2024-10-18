package com.demo.rbac.ecommerce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RbacEcommerceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacEcommerceGatewayApplication.class, args);
	}

}
