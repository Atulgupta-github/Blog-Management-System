package com.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // Marks this class as a Spring Bean so it can be injected into other components
public class JwtTokenHelper {
    
    // Token validity duration (in seconds) — here: 5 * 50 * 60 = 15,000 seconds = 4 hours 10 minutes
    public static final long JWT_TOKEN_VALIDITY = 5 * 50 * 60;

    // Secret key used to sign the JWT token
    private String secret = "jwtTokenKey";

    // Extracts the username (subject) from the token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Extracts the expiration date from the token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Generic method to extract any claim using a resolver function
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Parses the token and retrieves all claims using the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    }

    // Checks if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expireDate = getExpirationDateFromToken(token);
        return expireDate.before(new Date());
    }

    // Generates a new JWT token for the given user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(); // Add custom claims if needed
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Core logic to build and sign the token
    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims) // set additional data inside the token
                .setSubject(username) // set the username as subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // expiration time
                .signWith(SignatureAlgorithm.HS512, secret) // sign using HS512 algorithm and secret key
                .compact(); // builds and returns the JWT as a compact string
    }

    // Validates the token by checking if it belongs to the user and is not expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
