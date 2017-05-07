package com.bsuir.petition.security.service.impl;

import com.bsuir.petition.security.service.GetTokenService;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.security.util.SecurityUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServiceImpl implements GetTokenService {

    private final static String TOKEN_KEY = "petition";
    private final static String API_KEY = "apikey";
    private final static String EMAIL = "EMAIL";
    private final static String USER_ID = "USER_ID";
    private final static String IS_ADMIN = "IS_ADMIN";
    private final static String EXPIRATION_DATE = "TOKEN_EXPIRATION_DATE";

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String getToken(String email, String password) throws AuthenticationException {

        if (email == null || password == null) {
            return null;
        }

        SecurityUser user;
        try {
            user = (SecurityUser) userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException exception) {
            throw new AuthenticationException("No user with suh email!", exception);
        }

        Map<String, Object> tokenData = new HashMap<String, Object>();

        if (password.equals(user.getPassword())) {
            tokenData.put(USER_ID, user.getId());
            tokenData.put(EMAIL, user.getUsername());

            boolean check = user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));

            tokenData.put(IS_ADMIN, check);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put(EXPIRATION_DATE, calendar.getTime());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);

            return jwtBuilder.signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
        } else {
            throw new AuthenticationException("Wrong password!");
        }
    }
}
