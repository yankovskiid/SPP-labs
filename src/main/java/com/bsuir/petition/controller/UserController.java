package com.bsuir.petition.controller;


import com.bsuir.petition.bean.dto.request.UserLoginDTO;
import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.dto.response.message.TokenDTO;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.exception.user.DifferentPasswordsException;
import com.bsuir.petition.service.exception.user.ErrorInputException;
import com.bsuir.petition.service.exception.user.SuchUserExistsException;
import com.bsuir.petition.service.exception.user.UserInformationNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserController {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    UserInformationDTO getUser(@PathVariable long id) throws UserInformationNotFoundException;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    TokenDTO getLogin(@RequestBody UserRegistrationDTO userRegistrationDTO)
            throws AuthenticationException, DifferentPasswordsException, ErrorInputException, SuchUserExistsException;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    TokenDTO getSignUp(@RequestBody UserLoginDTO userLoginDTO) throws AuthenticationException;
}
