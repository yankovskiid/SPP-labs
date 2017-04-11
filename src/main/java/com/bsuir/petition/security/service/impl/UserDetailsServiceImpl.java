package com.bsuir.petition.security.service.impl;

import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.security.util.SecurityUser;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.user.UserService;
import com.bsuir.petition.service.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SecurityUser loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        try {
            userEmail = URLDecoder.decode(userEmail, "UTF-8");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        User user = null;

        try {
            user = userService.getUser(userEmail);
        } catch (UserNotFoundException | ErrorInputException e) {
            e.printStackTrace();
        }

        if (user == null) {
            throw new UsernameNotFoundException("User " + userEmail + " not found!");
        }

        List<GrantedAuthority> authorities = getUserRoles(user);

        return new SecurityUser(user.getEmail(), user.getPassword(), user.getId(),
                !user.isBlocked(), true, true, true, authorities);

    }

    private List<GrantedAuthority> getUserRoles(User user) {
        List<GrantedAuthority> result = new ArrayList<>(0);
        Set<Role> roleSet = user.getRoles();
        roleSet.forEach(role ->  {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            result.add(grantedAuthority);
        });
        return result;
    }
}
