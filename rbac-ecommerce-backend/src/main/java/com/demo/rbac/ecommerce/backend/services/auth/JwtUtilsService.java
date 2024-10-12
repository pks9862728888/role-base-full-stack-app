package com.demo.rbac.ecommerce.backend.services.auth;

import com.demo.rbac.ecommerce.backend.config.security.configparams.SecurityConfigParams;
import com.demo.rbac.ecommerce.backend.entities.users.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtilsService {
    private final SecurityConfigParams securityConfigParams;
    private JwtParser jwtParser;
    public static final String BEARER = "Bearer";
    public static final String ROLES = "Roles";

    @PostConstruct
    public void init() {
        this.jwtParser = Jwts.parser().verifyWith(getSecretKey()).build();
    }

    public String extractBearerToken(String authorizationToken) {
        return StringUtils.substringAfter(authorizationToken, BEARER).trim();
    }

    public String createJwtToken(UserAccount userAccount) {
        Date iat = new Date();
        return Jwts.builder()
                .subject(userAccount.getUsername())
                .issuer(securityConfigParams.getJwt().getIssuer())
                .issuedAt(iat)
                .expiration(new Date(iat.getTime() +
                        TimeUnit.MINUTES.toMillis(securityConfigParams.getJwt().getTokenExpiryMin())))
                .claims(Map.of(ROLES, userAccount.getAuthorities()))
                .signWith(getSecretKey())
                .compact();
    }

    public Jws<Claims> parseToken(String jwt) {
        return this.jwtParser.parseSignedClaims(jwt);
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(securityConfigParams.getJwt().getSecretKey().getBytes());
    }

    public boolean isTokenValid(Jws<Claims> parsedJwt) {
        boolean isTokenValid = true;
        Claims claims = parsedJwt.getPayload();
        if (!securityConfigParams.getJwt().getIssuer().equals(claims.getIssuer())) {
            log.warn("Not a valid issuer: {}", claims.getIssuer());
            isTokenValid = false;
        }
        if (claims.getExpiration().toInstant().isBefore(new Date().toInstant())) {
            log.warn("Token expired!");
            isTokenValid = false;
        }
        return isTokenValid;
    }
}
