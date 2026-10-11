package com.CRUDexample.CRUDdemo.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {
    
    private final String SECRET_KEY = "MyVerySecretKeyForJwtAuthentication123456789";

    private final SecretKey key =
        Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {

        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(
                new Date(System.currentTimeMillis() + 1000 * 60 * 60)
            )
            .signWith(key)
            .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
}
