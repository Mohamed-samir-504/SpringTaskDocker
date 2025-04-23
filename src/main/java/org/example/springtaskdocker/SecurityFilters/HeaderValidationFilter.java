package org.example.springtaskdocker.SecurityFilters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HeaderValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String headerValue = request.getHeader("x-validation-report");
        String path = request.getRequestURI();

        if (path.startsWith("/delete") || path.startsWith("/update")) {
            if (!"true".equalsIgnoreCase(headerValue)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Missing or invalid x-validation-report header");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }
}

