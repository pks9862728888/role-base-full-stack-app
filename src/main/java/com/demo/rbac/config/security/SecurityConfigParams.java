package com.demo.rbac.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityConfigParams {
    private String secretKey;
    private long tokenExpiryMin;
    private String issuer;
}
