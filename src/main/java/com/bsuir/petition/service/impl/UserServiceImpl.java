package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(long id) {
        User user;
        user = userDao.getUserById(id);
        return user;
    }
}
