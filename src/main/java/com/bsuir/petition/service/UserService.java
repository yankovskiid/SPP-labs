package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.exception.user.*;

public interface UserService {
    User getUser(String userEmail) throws UserNotFoundException;
    UserInformation getUserInformation(long id) throws UserInformationNotFoundException;
    User registration(UserRegistrationDTO userRegistrationDTO)
            throws SuchUserExistsException, ErrorInputException, DifferentPasswordsException;

    void updateUserInformation(long id, UserInformationDTO userInformationDTO)
            throws ErrorInputException, UserInformationNotFoundException;
}
