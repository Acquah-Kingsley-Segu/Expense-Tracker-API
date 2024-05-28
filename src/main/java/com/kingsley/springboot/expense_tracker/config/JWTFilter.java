package com.kingsley.springboot.expense_tracker.config;

import com.kingsley.springboot.expense_tracker.service.JWTService;
import com.kingsley.springboot.expense_tracker.service.implementations.SecurityUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final SecurityUserDetailService userDetailService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String userJWT = authorizationHeader.substring(7);
        if (jwtService.isTokenExpired(userJWT))
            throw new AuthenticationServiceException("Token has expired");
        String userClaim = jwtService.extractSubjectClaim(userJWT);
        SecurityUser user = (SecurityUser) userDetailService.loadUserByUsername(userClaim);
        if (!jwtService.isTokenSubjectValid(userJWT, user))
            throw new AuthenticationServiceException("Credential mismatch");
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication(user.getUsername(), true));
        filterChain.doFilter(request, response);
    }
}
