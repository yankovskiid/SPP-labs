package com.bsuir.petition.controller.impl;

import com.bsuir.petition.bean.dto.UserDTO;
import com.bsuir.petition.bean.dto.request.UserLoginDTO;
import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.dto.response.message.MessageDTO;
import com.bsuir.petition.bean.dto.response.message.TokenDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.controller.UserController;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.security.service.GetTokenService;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.DtoService;
import com.bsuir.petition.service.UserService;
import com.bsuir.petition.service.exception.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserControllerImpl implements UserController {

    private GetTokenService getTokenService;

    private UserService userService;

    private DtoService dtoService;

    @Autowired
    public void setGetTokenService(GetTokenService getTokenService) {
        this.getTokenService = getTokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDtoService(DtoService dtoService) {
        this.dtoService = dtoService;
    }


    @Override
    public UserDTO getUser(@PathVariable long id) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUser(@PathVariable long id,
                           @RequestBody UserDTO userDTO)
                            throws UserNotFoundException, ErrorInputException {

    }

    @Override
    public UserInformationDTO getUserInformation() throws UserInformationNotFoundException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        UserInformationDTO userInformationDTO;
        UserInformation userInformation = userService.getUserInformation((Long)tokenAuthentication.getDetails());
        userInformationDTO = dtoService.getUserInformationDTO(userInformation);
        return userInformationDTO;
    }

    @Override
    public void updateUserInformation(@RequestBody UserInformationDTO userInformationDTO)
            throws UserInformationNotFoundException, ErrorInputException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication)SecurityContextHolder.getContext().getAuthentication();
        userService.updateUserInformation((Long)tokenAuthentication.getDetails(), userInformationDTO);
    }

    @Override
    public UserInformationDTO getUserInformation(@PathVariable long id)
            throws UserInformationNotFoundException {
        UserInformationDTO userInformationDTO;
        UserInformation userInformation = userService.getUserInformation(id);
        userInformationDTO = dtoService.getUserInformationDTO(userInformation);
        return userInformationDTO;
    }

    @Override
    public void updateUserInformation(@PathVariable long id,
                                      @RequestBody UserInformationDTO userInformationDTO)
                                        throws UserInformationNotFoundException, ErrorInputException {
        userService.updateUserInformation(id, userInformationDTO);
    }

    @Override
    public TokenDTO registration(@RequestBody UserRegistrationDTO userRegistrationDTO)
            throws AuthenticationException, DifferentPasswordsException,
                    ErrorInputException, SuchUserExistsException {

        User user = userService.registration(userRegistrationDTO);
        String token;
        token = getTokenService.getToken(user.getEmail(), user.getPassword());
        return new TokenDTO(token);
    }

    @Override
    public TokenDTO login(@RequestBody UserLoginDTO userLoginDTO) throws AuthenticationException {
        String token;
        token = getTokenService.getToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return new TokenDTO(token);
    }
}