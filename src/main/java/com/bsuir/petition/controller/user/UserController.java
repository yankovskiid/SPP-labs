package com.bsuir.petition.controller.user;


import com.bsuir.petition.bean.dto.user.*;
import com.bsuir.petition.bean.dto.message.TokenDTO;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.exception.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserController {

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    UserListDTO getUsers() throws ServerException;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    UserDTO getUser(long id)
            throws UserNotFoundException, ServerException;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    void updateUser(long id, UpdateUserDTO updateUserDTO)
            throws UserNotFoundException, ErrorInputException, ServerException;

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = "/user/information", method = RequestMethod.GET)
    UserInformationDTO getUserInformation()
            throws UserInformationNotFoundException, ServerException;

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = "/user/information", method = RequestMethod.PUT)
    void updateUserInformation(UserInformationDTO userInformationDTO)
            throws UserInformationNotFoundException, ErrorInputException, ServerException;

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = "/user/information/add", method = RequestMethod.POST)
    void addUserInformation(UserInformationDTO userInformationDTO)
            throws UserInformationNotFoundException, ErrorInputException, ServerException;

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(value = "/user/information/{id}", method = RequestMethod.GET)
    UserInformationDTO getUserInformation(long id)
            throws UserInformationNotFoundException, ServerException;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    TokenDTO registration(UserRegistrationDTO userRegistrationDTO)
            throws AuthenticationException, DifferentPasswordsException, ErrorInputException, SuchUserExistsException, ServerException;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    TokenDTO login(UserLoginDTO userLoginDTO, HttpServletResponse request)
            throws AuthenticationException, ServerException;

    @RequestMapping(value = "/isAdmin", method = RequestMethod.GET)
    Boolean isAdmin() throws ServerException, UserNotFoundException;
}
