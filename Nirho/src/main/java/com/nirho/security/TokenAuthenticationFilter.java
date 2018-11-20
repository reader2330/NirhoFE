package com.nirho.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserDetailsService userDetailServiceImpl;

    private String getToken( HttpServletRequest request ) {                                  
        String authHeader = request.getHeader("Authorization");
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authToken = getToken( request );
    	request.setAttribute("username", tokenHelper.getUsernameFromToken(authToken));
    	request.setAttribute("role", tokenHelper.getRoleFromToken(authToken));
        chain.doFilter(request, response);
    }

}
