package com.nishtha.CarbonCredit.Security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key;
    private final long expiryMinutes;

    // Constructor injection: secret + expiry read from application.yml
    public JwtUtil(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiryMinutes}") long expiryMinutes
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiryMinutes = expiryMinutes * 60 * 1000; // convert minutes → ms
    }

    // Generate JWT token
    public String generateToken(String email, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("role", role))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiryMinutes))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ works in 0.11.x
                .compact();
    }

    // Parse token and validate signature
    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // Extract email from token
    public String extractEmail(String token) {
        return parse(token).getBody().getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return parse(token).getBody().get("role", String.class);
    }

    // Validate token expiration
    public boolean isTokenValid(String token) {
        try {
            Claims claims = parse(token).getBody();
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false; // invalid token
        }
    }
}
