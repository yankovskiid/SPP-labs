package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.service.exception.server.ServerException;
import com.bsuir.petition.service.exception.user.*;

public interface UserService {
    void updateUser(long id, UserDTO userDTO) throws UserNotFoundException, ServerException;
    User getUser(String userEmail) throws UserNotFoundException;
    UserDTO getUser(long id) throws UserNotFoundException, ServerException;
    UserInformationDTO getUserInformation(long id) throws UserInformationNotFoundException, ServerException;
    User registration(UserRegistrationDTO userRegistrationDTO)
            throws SuchUserExistsException, ErrorInputException,
                    DifferentPasswordsException, ServerException;

    void updateUserInformation(long id, UserInformationDTO userInformationDTO)
            throws ErrorInputException, ServerException;
}
