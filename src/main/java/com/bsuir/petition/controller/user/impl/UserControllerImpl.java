package com.bsuir.petition.controller.user.impl;

import com.bsuir.petition.bean.dto.message.TokenDTO;
import com.bsuir.petition.bean.dto.user.*;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.controller.user.UserController;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.security.service.GetTokenService;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.UserService;
import com.bsuir.petition.service.user.exception.DifferentPasswordsException;
import com.bsuir.petition.service.user.exception.SuchUserExistsException;
import com.bsuir.petition.service.user.exception.UserInformationNotFoundException;
import com.bsuir.petition.service.user.exception.UserNotFoundException;
import com.sun.security.ntlm.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


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
            throws UserInformationNotFoundException, ErrorInputException, ServerException, CityNotFoundException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication)SecurityContextHolder.getContext().getAuthentication();
        try {
            userService.updateUserInformation((Long) tokenAuthentication.getDetails(), userInformationDTO);
        } catch (CityNotFoundException e) {
            throw new CityNotFoundException("City not found!");
        }
    }

    @Override
    public void addUserInformation(UserInformationDTO userInformationDTO) throws UserInformationNotFoundException, ErrorInputException, ServerException, CityNotFoundException {
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

        String token;
        try {
            User user = userService.registration(userRegistrationDTO);
            token = getTokenService.getToken(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            throw new SuchUserExistsException("User with such email exists!");
        }
        return new TokenDTO(token);
    }

    @Override
    public TokenDTO login(@RequestBody UserLoginDTO userLoginDTO) throws AuthenticationException, ServerException {
        String token;
        String generatedPassword = userLoginDTO.getPassword();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update("salt".getBytes("UTF-8"));
            byte[] bytes = md.digest(userLoginDTO.getPassword().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        token = getTokenService.getToken(userLoginDTO.getEmail(), generatedPassword);
        return new TokenDTO(token);
    }

    @Override
    public Boolean isAdmin() throws ServerException, UserNotFoundException {
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDTO;
        userDTO = userService.getUser((Long)tokenAuthentication.getDetails());
        ArrayList<String> roles;
        roles = userDTO.getRoles();

        for(int i = 0; i < roles.size(); i++) {
            if(roles.get(i).equals("ADMIN")) {
                return true;
            }
        }

        return false;
    }
}