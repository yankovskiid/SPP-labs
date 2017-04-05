package com.bsuir.petition.controller;


import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserLoginDTO;
import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.dto.message.TokenDTO;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.exception.user.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserController {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    UserDTO getUser(long id)
            throws UserNotFoundException;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    void updateUser(long id, UserDTO userDTO)
            throws UserNotFoundException, ErrorInputException;

    @RequestMapping(value = "/user/information", method = RequestMethod.GET)
    UserInformationDTO getUserInformation()
            throws UserInformationNotFoundException;

    @RequestMapping(value = "/user/information", method = RequestMethod.POST)
    void updateUserInformation(UserInformationDTO userInformationDTO)
            throws UserInformationNotFoundException, ErrorInputException;


    @RequestMapping(value = "/user/information/{id}", method = RequestMethod.GET)
    UserInformationDTO getUserInformation(long id)
            throws UserInformationNotFoundException;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    TokenDTO registration(UserRegistrationDTO userRegistrationDTO)
            throws AuthenticationException, DifferentPasswordsException,
                    ErrorInputException, SuchUserExistsException;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    TokenDTO login(UserLoginDTO userLoginDTO)
            throws AuthenticationException;
}
