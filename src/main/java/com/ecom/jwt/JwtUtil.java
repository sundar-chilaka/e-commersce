package com.ecom.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtUtil {
	private static final String SECRET_KEY = "your-secret-key-your-secret-keylasfpoiewppokpopwerhgpmerjgpmofmpjwigo3[0g033goooh4";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
