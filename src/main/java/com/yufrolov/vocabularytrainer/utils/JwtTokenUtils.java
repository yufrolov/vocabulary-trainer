package com.yufrolov.vocabularytrainer.utils;

import com.yufrolov.vocabularytrainer.entity.Profile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
                .signWith(SignatureAlgorithm.HS256, secret)
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

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

}
