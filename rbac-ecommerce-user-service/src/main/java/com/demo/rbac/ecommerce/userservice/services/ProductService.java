package com.demo.rbac.ecommerce.userservice.services;

import com.demo.rbac.ecommerce.persistence.entitites.product.Product;
import com.demo.rbac.ecommerce.persistence.repositories.product.ProductRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;

    @Value("${PRODUCT_CATALOG_SERVICE}")
    private String productCatalogService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void getDataFromProductCatalog() {
        Optional<String> baseUriOpt = getProductCatalogServiceBaseUri();
        if (baseUriOpt.isPresent()) {
            String baseUri = baseUriOpt.get();
            baseUri = baseUri + "api/v1/test";
            String response = restTemplate.getForEntity(baseUri, String.class).getBody();
            log.info("Got response: {} from uri: {}", response, baseUri);
        } else {
            log.info("Handle failure scenario: {} not available", productCatalogService);
        }
    }

    private Optional<String> getProductCatalogServiceBaseUri() {
        try {
            InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka(productCatalogService, false);
            return Optional.of(nextServerFromEureka.getHomePageUrl());
        } catch (Exception e) {
            log.error("Exception occurred while discovering service: {} ex: {}", productCatalogService, e.toString());
            return Optional.empty();
        }
    }
}
