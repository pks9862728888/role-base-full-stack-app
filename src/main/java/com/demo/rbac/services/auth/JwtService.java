package com.demo.rbac.services.auth;

import com.demo.rbac.config.security.SecurityConfigParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final SecurityConfigParams securityConfigParams;


}
