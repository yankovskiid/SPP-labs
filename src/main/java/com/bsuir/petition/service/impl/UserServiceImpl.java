package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.UserService;
import com.bsuir.petition.service.exception.server.ServerException;
import com.bsuir.petition.service.exception.user.*;
import com.bsuir.petition.service.util.DtoService;
import com.bsuir.petition.service.util.Exchanger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private CityDao cityDao;

    private UserDao userDao;

    private DtoService dtoExchanger;

    private Exchanger exchanger;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Autowired
    public void setDtoExchanger(DtoService dtoExchanger) { this.dtoExchanger = dtoExchanger; }

    @Autowired
    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updateUser(long id, UserDTO userDTO) throws UserNotFoundException, ServerException {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
        if (user == null) {
            throw new UserNotFoundException("No such user!");
        }
        try {
            userDao.updateUser(user);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
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
    public UserDTO getUser(long id) throws UserNotFoundException, ServerException {
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (Exception exception) {
            throw new UserNotFoundException(exception);
        }
        UserDTO userDTO;
        userDTO = dtoExchanger.getUserDTO(user);
        return userDTO;
    }

    @Override
    public UserInformationDTO getUserInformation(long id)
                        throws UserInformationNotFoundException, ServerException {
        UserInformation userInformation;
        try {
            userInformation = userDao.getUserInformationById(id);
            if (userInformation == null) {
                throw new UserInformationNotFoundException("No such user information!");
            }
        } catch (HibernateException | NullPointerException exception) {
            throw new ServerException("Server exception!", exception);
        }

        return dtoExchanger.getUserInformationDTO(userInformation);
    }

    @Override
    public User registration(UserRegistrationDTO userRegistrationDTO)
            throws DifferentPasswordsException, ErrorInputException,
            SuchUserExistsException, ServerException {

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
    public void updateUserInformation(long id, UserInformationDTO userInformationDTO)
            throws ErrorInputException, ServerException {

        UserInformation userInformation;
        userInformation = userDao.getUserInformationById(id);

        try {
            userDao.updateUserInformation(userInformation);
            if (userInformation == null) {
                userInformation = getUserInformation(id, userInformationDTO.getCity());
            }
            userDao.updateUserInformation(userInformation);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
    }

    private UserInformation getUserInformation(long id, String cityName) {
        UserInformation userInformation = new UserInformation();

        User user = userDao.getUserById(id);
        userInformation.setUser(user);

        City city = cityDao.getCityByName(cityName);
        userInformation.setCity(city);

        return userInformation;
    }

}
