package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.UserService;
import com.bsuir.petition.service.exception.user.*;
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
    public User getUser(String userEmail) throws UserNotFoundException {
        User user;
        try {
            user = userDao.getUserByEmail(userEmail);
        } catch (Exception exception) {
            throw new UserNotFoundException(exception);
        }
        return user;
    }

    @Override
    public UserInformation getUserInformation(long id) throws UserInformationNotFoundException {
        UserInformation userInformation;
        try {
            userInformation = userDao.getUserInformationById(id);
        } catch (Exception exception) {
            throw new UserInformationNotFoundException("No such user information!", exception);
        }
        return userInformation;
    }

    @Override
    public User registration(UserRegistrationDTO userRegistrationDTO)
            throws DifferentPasswordsException, ErrorInputException, SuchUserExistsException {

        User user;
        if (userRegistrationDTO.getRepeatPassword().equals(userRegistrationDTO.getPassword())) {

            try {
                user = exchanger.getUser(userRegistrationDTO);
                userDao.addUser(user);
            } catch (Exception exception) {
                throw new SuchUserExistsException("User with such name exists!", exception);
            }
        } else {
            throw new DifferentPasswordsException("Password and repeat password, must be equals!");
        }
        return user;
    }

    @Override
    public void updateUserInformation(long id, UserInformationDTO userInformationDTO) throws ErrorInputException, UserInformationNotFoundException {

    }


}
