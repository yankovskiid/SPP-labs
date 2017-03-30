package com.bsuir.petition.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static String TOKEN = "token";

    public TokenAuthenticationFilter() {
        super("/user/**");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                                                throws AuthenticationException, IOException, ServletException {
        String token;

        token = request.getHeader(TOKEN);

        if (token == null) {
            token = request.getParameter(TOKEN);
        }

        if (token == null) {
            TokenAuthentication tokenAuthentication = new TokenAuthentication(null, null);
            try {
                tokenAuthentication.setAuthenticated(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return tokenAuthentication;
        }

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        return authentication;
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
