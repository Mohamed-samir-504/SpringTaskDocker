package org.example.springtaskdocker.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class HeaderValidationFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/swagger-ui",
            "/v3/api-docs",
            "/form",
            "/Add",
            "/new-course"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        String headerValue = request.getHeader("x-validation-report");
        String path = request.getRequestURI();

        if (EXCLUDED_PATHS.stream().noneMatch(path::startsWith)) {
            if (!"true".equalsIgnoreCase(headerValue)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Missing or invalid x-validation-report header");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

