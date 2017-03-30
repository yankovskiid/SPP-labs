package com.bsuir.petition.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

public class TokenAuthenticationManager implements AuthenticationManager {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {

            if (authentication instanceof TokenAuthentication) {
                return processAuthentication((TokenAuthentication) authentication);
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }

        } catch (Exception exception) {
            if ( exception instanceof AuthenticationServiceException) {
                throw exception;
            }

        }
        return null;
    }

    private TokenAuthentication processAuthentication(TokenAuthentication tokenAuthentication)
                                                        throws AuthenticationException {
        String token = tokenAuthentication.getToken();
        String key = "abc123";
        DefaultClaims claims;

        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }

        if (claims.get("TOKEN_EXPIRATION_DATE", Long.class) == null) {
            throw new AuthenticationServiceException("Invalid token");
        }

        Date expiredDate = new Date(claims.get("TOKEN_EXPIRATION_DATE", Long.class));
        if (expiredDate.after(new Date())) {
            return buildFullTokenAuthentication(tokenAuthentication, claims);
        } else {
            throw new AuthenticationServiceException("Token expired date error");
        }
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, DefaultClaims claims)
                                                                throws AuthenticationServiceException {
        User user = (User) userDetailsService.loadUserByUsername(claims.get("EMAIL", String.class));
        if (user.isEnabled()) {
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            return new TokenAuthentication(authentication.getToken(), authorities, true, user);
        } else {
            throw new AuthenticationServiceException("User disabled");
        }
    }
}
