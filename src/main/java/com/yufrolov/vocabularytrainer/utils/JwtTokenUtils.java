package com.yufrolov.vocabularytrainer.utils;

import com.yufrolov.vocabularytrainer.entity.Profile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String generateToken(Profile profile) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", profile.getRole().getTitle());
        claims.put("id", profile.getId());

        var issueDate = new Date();
        var expiredDate = new Date(issueDate.getTime() + lifetime.toMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(profile.getEmail())
                .issuedAt(issueDate)
                .expiration(expiredDate)
                .signWith(getSecKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String getTokenFromHeaders(String header) {
        return header.substring(7);
    }

    public String getUserName(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getRole(String token) {
        return getAllClaimsFromToken(token).get("roles", String.class);
    }

    public UUID getId(String token) {
        return UUID.fromString(getAllClaimsFromToken(token).get("id", String.class));
    }

    private SecretKey getSecKey(){
        var keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
