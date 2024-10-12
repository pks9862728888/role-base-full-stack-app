package com.demo.rbacecommerceeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RbacEcommerceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacEcommerceEurekaServerApplication.class, args);
	}
}
