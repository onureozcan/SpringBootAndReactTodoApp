package org.zero.todoapp.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.zero.todoapp.Constants;
import org.zero.todoapp.exceptions.TokenValidationException;
import org.zero.todoapp.security.WebTokenProvider;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/**")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.equals("/auth/login") || path.equals("/auth/register")) {
            filterChain.doFilter(request, response);
        } else {
            String jwt = request.getHeader("authorization");
            if (jwt == null || jwt.equals("null")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                String userName = null;
                try {
                    userName = WebTokenProvider.verifyToken(jwt);
                    request.setAttribute(Constants.STR_USER_NAME, userName);
                    if (userName != null) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } catch (TokenValidationException ex) {
                    ex.printStackTrace();
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }
}