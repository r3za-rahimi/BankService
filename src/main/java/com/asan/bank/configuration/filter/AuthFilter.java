package com.asan.bank.configuration.filter;

import com.asan.bank.security.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Autowired
    private AuthService authService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.err.println(req.getHeader("Authorization"));
        if ((req.getHeader("Authorization") == null) || !authService.isTokenValid(req.getHeader("Authorization")).getIsValid()) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized - Please Log In");

        } else {

            chain.doFilter(request, response);

        }
    }
}
