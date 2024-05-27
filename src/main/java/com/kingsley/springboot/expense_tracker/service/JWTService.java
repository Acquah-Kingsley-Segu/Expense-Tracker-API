package com.kingsley.springboot.expense_tracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("jwt.expiration.time")
    private String expiration;

    public String generateToken(Map<String, Object> extraClaims, String email){
        return this.buildToken(extraClaims, email);
    }
    public String extractSubjectClaim(String userJWT){
        return extractClaim(userJWT, Claims::getSubject);
    }

    public Date extractExpirationClaim(String userJWT){
        return extractClaim(userJWT, Claims::getExpiration);
    }

    public boolean isTokenSubjectValid(String userJWT, UserDetails user){
        return this.extractSubjectClaim(userJWT).equals(user.getUsername());
    }

    public boolean isTokenExpired(String userJWT){
        return extractExpirationClaim(userJWT).before(new Date());
    }

    // build jwt token from user details and claims
    private String buildToken(Map<String, Object> extraClaims, String subject){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // parse jwt token of user and extracts payload from jwt
    private Claims extractAllClaims(String tokenToExtractClaims){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(tokenToExtractClaims)
                .getBody();
    }

    // extracts a single claim from token based on claim function type
    private <T> T extractClaim(String tokenToExtractClaim, Function<Claims, T> claimsResolver){
        Claims claimsExtracted = this.extractAllClaims(tokenToExtractClaim);
        return claimsResolver.apply(claimsExtracted);
    }

    // create signature key
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
