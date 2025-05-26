package com.example.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();


        if (path.equals("/user") && req.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }
        if (path.equals("/user/login")) {
            chain.doFilter(request, response);
            return;
        }

        // 세션에 userId가 없으면 401
        Object userId = req.getSession().getAttribute("userId");
        if (userId == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("인증이 필요합니다.");
            return;
        }

        chain.doFilter(request, response);
    }
}