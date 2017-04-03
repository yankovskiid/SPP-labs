package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.UserService;
import com.bsuir.petition.service.exception.user.DifferentPasswordsException;
import com.bsuir.petition.service.exception.user.ErrorInputException;
import com.bsuir.petition.service.exception.user.SuchUserExistsException;
import com.bsuir.petition.service.exception.user.UserInformationNotFoundException;
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
    public User getUser(String userEmail) {
        User user;
        user = userDao.getUserByEmail(userEmail);
        return user;
    }

    @Override
    public UserInformation getUserInformation(long id) throws UserInformationNotFoundException {
        UserInformation userInformation;
        userInformation = userDao.getUserInformation(id);
        return userInformation;
    }

    @Override
    public void registration(UserRegistrationDTO userRegistrationDTO)
            throws DifferentPasswordsException, ErrorInputException, SuchUserExistsException {

        if (userRegistrationDTO.getRepeatPassword().equals(userRegistrationDTO.getPassword())) {

            try {
                User user = exchanger.getUser(userRegistrationDTO);
                userDao.addUser(user);
            } catch (Exception exception) {
                throw new SuchUserExistsException("User with such name exists!", exception);
            }
        } else {
            throw new DifferentPasswordsException("Password and repeat password, must be equals!");
        }
    }


}
