package com.demo.rbac.ecommerce.common.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_BUYER,
    ROLE_SELLER;

    @Override
    public String getAuthority() {
        return name();
    }
}
