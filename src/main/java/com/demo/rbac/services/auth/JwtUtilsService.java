package com.demo.rbac.services.auth;

import com.demo.rbac.config.security.SecurityConfigParams;
import com.demo.rbac.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtilsService {
    private final SecurityConfigParams securityConfigParams;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        this.jwtParser = Jwts.parser().verifyWith(getSecretKey()).build();
    }

    public String createJwtToken(User user) {
        Date iat = new Date();
        return Jwts.builder()
                .subject(user.getUsername())
                .issuer(securityConfigParams.getIssuer())
                .issuedAt(iat)
                .expiration(new Date(iat.getTime() +
                        TimeUnit.MINUTES.toMillis(securityConfigParams.getTokenExpiryMin())))
                .signWith(getSecretKey())
                .compact();
    }

    public Claims resolveClaims(String jwt) {
        return this.jwtParser.parseSignedClaims(jwt).getPayload();
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(securityConfigParams.getSecretKey().getBytes());
    }

    public boolean isTokenValid(Claims claims) {
        boolean isTokenValid = true;
        if (claims.getExpiration().toInstant().isBefore(new Date().toInstant())) {
            log.warn("Token expired!");
            isTokenValid = false;
        }
        if (!securityConfigParams.getIssuer().equals(claims.getIssuer())) {
            log.warn("Not a valid issuer: {}", claims.getIssuer());
            isTokenValid = false;
        }
        return isTokenValid;
    }
}
