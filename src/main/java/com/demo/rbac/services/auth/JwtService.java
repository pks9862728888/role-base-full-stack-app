package com.demo.rbac.services.auth;

import com.demo.rbac.config.security.SecurityConfigParams;
import com.demo.rbac.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final SecurityConfigParams securityConfigParams;

    public String createJwtToken(User user) {
        Date iat = new Date();
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(iat)
                .expiration(new Date(iat.getTime() +
                        TimeUnit.MINUTES.toMillis(securityConfigParams.getTokenExpiryMin())))
                .signWith(Keys.hmacShaKeyFor(securityConfigParams.getSecretKey().getBytes()))
                .compact();
    }
}
