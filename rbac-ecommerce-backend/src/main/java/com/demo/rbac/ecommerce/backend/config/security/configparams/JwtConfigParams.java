package com.demo.rbac.ecommerce.backend.config.security.configparams;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtConfigParams {
    @ToString.Exclude
    private String secretKey;
    private long tokenExpiryMin;
    private String issuer;
}
