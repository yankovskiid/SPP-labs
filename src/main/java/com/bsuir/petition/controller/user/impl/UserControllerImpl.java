package com.bsuir.petition.controller.user.impl;

import com.bsuir.petition.bean.dto.message.TokenDTO;
import com.bsuir.petition.bean.dto.user.*;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.controller.user.UserController;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.security.service.GetTokenService;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.UserService;
import com.bsuir.petition.service.user.exception.DifferentPasswordsException;
import com.bsuir.petition.service.user.exception.SuchUserExistsException;
import com.bsuir.petition.service.user.exception.UserInformationNotFoundException;
import com.bsuir.petition.service.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserControllerImpl implements UserController {

    private GetTokenService getTokenService;

    private UserService userService;

    @Autowired
    public void setGetTokenService(GetTokenService getTokenService) {
        this.getTokenService = getTokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserListDTO getUsers() throws ServerException {
        UserListDTO userListDTO;
        userListDTO = userService.getUsers();
        return userListDTO;
    }

    @Override
    public UserDTO getUser(@PathVariable long id) throws UserNotFoundException, ServerException {
        UserDTO userDTO;
        userDTO = userService.getUser(id);
        return userDTO;
    }

    @Override
    public void updateUser(@PathVariable long id,
                           @RequestBody UpdateUserDTO updateUserDTO)
            throws UserNotFoundException, ErrorInputException, ServerException {
        userService.updateUser(id, updateUserDTO);
    }

    @Override
    public UserInformationDTO getUserInformation() throws UserInformationNotFoundException, ServerException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        UserInformationDTO userInformationDTO;
        userInformationDTO = userService.getUserInformation((Long)tokenAuthentication.getDetails());
        return userInformationDTO;
    }

    @Override
    public void updateUserInformation(@RequestBody UserInformationDTO userInformationDTO)
            throws UserInformationNotFoundException, ErrorInputException, ServerException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication)SecurityContextHolder.getContext().getAuthentication();
        userService.updateUserInformation((Long)tokenAuthentication.getDetails(), userInformationDTO);
    }

    @Override
    public void addUserInformation(UserInformationDTO userInformationDTO) throws UserInformationNotFoundException, ErrorInputException, ServerException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication)SecurityContextHolder.getContext().getAuthentication();
        userService.updateUserInformation((Long)tokenAuthentication.getDetails(), userInformationDTO);
    }

    @Override
    public UserInformationDTO getUserInformation(@PathVariable long id)
            throws UserInformationNotFoundException, ServerException {
        UserInformationDTO userInformationDTO;
        userInformationDTO = userService.getUserInformation(id);
        return userInformationDTO;
    }

    @Override
    public TokenDTO registration(@RequestBody UserRegistrationDTO userRegistrationDTO)
            throws AuthenticationException, DifferentPasswordsException, ErrorInputException, SuchUserExistsException, ServerException {

        User user = userService.registration(userRegistrationDTO);
        String token;
        token = getTokenService.getToken(user.getEmail(), user.getPassword());
        return new TokenDTO(token);
    }

    @Override
    public TokenDTO login(@RequestBody UserLoginDTO userLoginDTO) throws AuthenticationException, ServerException {
        String token;
        token = getTokenService.getToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return new TokenDTO(token);
    }
}