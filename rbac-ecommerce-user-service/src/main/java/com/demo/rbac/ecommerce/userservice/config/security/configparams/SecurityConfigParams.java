package com.demo.rbac.ecommerce.userservice.config.security.configparams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
@ToString(callSuper = true)
public class SecurityConfigParams {
    private JwtConfigParams jwt;
    private List<String> corsAllowedOrigins;
}
