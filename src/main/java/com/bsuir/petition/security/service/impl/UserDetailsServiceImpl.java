package com.bsuir.petition.security.service.impl;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.security.util.SecurityUser;
import com.bsuir.petition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public SecurityUser loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        try {
            userEmail = URLDecoder.decode(userEmail, "UTF-8");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        User user = userService.getUser(userEmail);

        if (user == null) {
            System.out.println("No such user");
            throw new UsernameNotFoundException("User " + userEmail + " not found!");
        }

        return new SecurityUser(user.getEmail(), user.getPassword(), user.getId(),
                !user.isBlocked(), true, true, true, AuthorityUtils.createAuthorityList("USER"));

    }
}
