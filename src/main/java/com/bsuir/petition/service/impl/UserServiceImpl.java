package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.UserService;
import com.bsuir.petition.service.util.Exchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private Exchanger exchanger;

    @Autowired
    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(long id) {
        User user;
        user = userDao.getUserById(id);
        return user;
    }

    @Override
    public User getUser(String userEmail) {
        User user;
        user = userDao.getUserByEmail(userEmail);
        return user;
    }

    @Override
    public UserInformation getUserInformation(long id) {
        UserInformation userInformation;
        userInformation = userDao.getUserInformation(id);
        return userInformation;
    }

    @Override
    public void registration(UserRegistrationDTO userRegistrationDTO) {

        if (userRegistrationDTO.getRepeatPassword().equals(userRegistrationDTO.getPassword())) {

            User user = exchanger.getUser(userRegistrationDTO);
            userDao.addUser(user);
        } else {
            //TODO: Throw exception
        }
    }


}
