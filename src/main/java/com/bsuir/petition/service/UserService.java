package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.exception.user.*;

public interface UserService {
    User getUser(String userEmail);
    UserInformation getUserInformation(long id) throws UserInformationNotFoundException;
    void registration(UserRegistrationDTO userRegistrationDTO)
            throws SuchUserExistsException, ErrorInputException, DifferentPasswordsException;
}
