package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component // Marks this as a Spring-managed bean
public class JwtAuthenticationOncePreReqFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService; // Service to load user details by username

    @Autowired
    private JwtTokenHelper jwtTokenHelper; // Helper to extract/validate JWT token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1: Get token from Authorization header
        String requestToken = request.getHeader("Authorization");

        System.out.println(requestToken); // Debug print

        String username = null;
        String token = null;

        // Step 2: Check if token is present and starts with "Bearer"
        if (request != null && requestToken != null && requestToken.startsWith("Bearer")) {
            // Extract the actual token part after "Bearer "
            token = requestToken.substring(7);
            try {
                // Extract username from the token
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("Unable to get JWT token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT token expired!!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                System.out.println("Invalid JWT Token");
            }
        } else {
            // If header doesn't start with Bearer or is null
            System.out.println("JWT token does not begin with Bearer");
        }

        // Step 3: If username is extracted and no auth is set in the security context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from database or in-memory
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Validate token
            if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                // Create authentication object with user's authorities
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Attach request details to the authentication token
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            } else {
                System.out.println("Invalid JWT token");
            }

        } else {
            // Either username is null or someone is already authenticated
            System.out.println("Username is null or context is not null");
        }

        // Step 4: Continue with the filter chain
        filterChain.doFilter(request, response);
    }

}
