package com.bsuir.petition.security.service;

import com.bsuir.petition.security.util.SecurityUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServiceImpl {

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String getToken(String email, String password) throws Exception {

        if (email == null || password == null) {
            return null;
        }

        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(email);
        Map<String, Object> tokenData = new HashMap<String, Object>();

        if (password.equals(user.getPassword())) {
            tokenData.put("clintType", "USER");
            tokenData.put("userID", user.getId());
            tokenData.put("email", user.getUsername());

            tokenData.put("creationDate", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put("tokenExpirationDate", calendar.getTime());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);

            String key = "abc123";
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

            return token;
        } else {
            throw new Exception("Authentication error");
        }
    }
}
