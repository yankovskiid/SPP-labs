package com.bsuir.petition.service.user.impl;

import com.bsuir.petition.bean.dto.user.*;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.UserService;
import com.bsuir.petition.service.user.exception.DifferentPasswordsException;
import com.bsuir.petition.service.user.exception.SuchUserExistsException;
import com.bsuir.petition.service.user.exception.UserInformationNotFoundException;
import com.bsuir.petition.service.user.exception.UserNotFoundException;
import com.bsuir.petition.service.user.util.UserCreator;
import com.bsuir.petition.service.user.util.UserDataValidator;
import com.bsuir.petition.service.user.util.UserDtoExchanger;
import com.bsuir.petition.service.user.util.UserExchanger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private UserCreator userCreator;

    private UserDataValidator userDataValidator;

    private UserDao userDao;

    private UserDtoExchanger userDtoExchanger;

    private UserExchanger userExchanger;

    @Autowired
    public void setUserDataValidator(UserDataValidator userDataValidator) {
        this.userDataValidator = userDataValidator;
    }

    @Autowired
    public void setUserCreator(UserCreator userCreator) {
        this.userCreator = userCreator;
    }

    @Autowired
    public void setUserDtoExchanger(UserDtoExchanger userDtoExchanger) { this.userDtoExchanger = userDtoExchanger; }

    @Autowired
    public void setUserExchanger(UserExchanger userExchanger) {
        this.userExchanger = userExchanger;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserListDTO getUsers() throws ServerException {
        UserListDTO userListDTO;
        try {
            List<User> users;
            users = userDao.getUsers();
            userListDTO = userDtoExchanger.getUserListDTO(users);
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }
        return userListDTO;
    }

    @Override
    public void updateUser(long id, UpdateUserDTO updateUserDTO) throws UserNotFoundException, ServerException, ErrorInputException {
        User user;

        userDataValidator.validate(updateUserDTO);

        try {
            user = userDao.getUserById(id);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }

        if (user == null) {
            throw new UserNotFoundException("No such user!");
        }

        userExchanger.getUser(user, updateUserDTO);

        try {
            userDao.updateUser(user);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
    }

    @Override
    public User getUser(String userEmail) throws UserNotFoundException, ErrorInputException {

        userDataValidator.validate(userEmail);

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
            if (user == null) {
                throw new UserNotFoundException("Such user not found!");
            }
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }

        UserDTO userDTO;
        userDTO = userDtoExchanger.getUserDTO(user);
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
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }

        return userDtoExchanger.getUserInformationDTO(userInformation);
    }

    @Override
    public User registration(UserRegistrationDTO userRegistrationDTO)
            throws DifferentPasswordsException, ErrorInputException,
            SuchUserExistsException, ServerException {

        User user;

        userDataValidator.validate(userRegistrationDTO);

        if (userRegistrationDTO.getRepeatPassword().equals(userRegistrationDTO.getPassword())) {

            try {
                String generatedPassword;
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update("salt".getBytes("UTF-8"));
                byte[] bytes = md.digest(userRegistrationDTO.getPassword().getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();

                String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                boolean isValid = Pattern.matches(emailRegex, userRegistrationDTO.getEmail());
                if (!isValid) {
                    throw new ErrorInputException("Email is not valid");
                }

                userRegistrationDTO.setPassword(generatedPassword);
                user = userExchanger.getUser(userRegistrationDTO);
                userDao.addUser(user);
            } catch (HibernateException exception) {
                throw new SuchUserExistsException("User with such name exists!", exception);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new DifferentPasswordsException("Password and repeat password, must be equals!");
        }
        return user;
    }

    @Override
    public void updateUserInformation(long id, UserInformationDTO userInformationDTO)
            throws ErrorInputException, ServerException, CityNotFoundException {

        userDataValidator.validate(userInformationDTO);

        UserInformation userInformation;
        userInformation = userDao.getUserInformationById(id);

        try {
            if (userInformation == null) {
                User user = userDao.getUserById(id);
                userInformation = userCreator.getUserInformation(user, userInformationDTO.getCity());
            }
            userExchanger.setUserInformation(userInformation, userInformationDTO);
            userDao.updateUserInformation(userInformation);
        } catch (CityNotFoundException e) {
            throw new CityNotFoundException("City not found!");
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }
    }

    @Override
    public void addUserInformation(long id, UserInformationDTO userInformationDTO) throws ErrorInputException, ServerException, CityNotFoundException {
        userDataValidator.validate(userInformationDTO);

        UserInformation userInformation = new UserInformation();

        try {
            userExchanger.setUserInformation(userInformation, userInformationDTO);
            userDao.addUserInformation(userInformation);
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        } catch (CityNotFoundException e) {
            throw new CityNotFoundException("City not found!");
        }
    }

}
