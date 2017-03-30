package com.bsuir.petition.controller;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User getLogin() {
        User user = userService.getUser(1);
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public User getSignUp() {
        User user = userService.getUser(1);
        return user;
    }

    @RequestMapping(value = "/registration/", method = RequestMethod.POST)
    public void addUser(User user) {

    }
}