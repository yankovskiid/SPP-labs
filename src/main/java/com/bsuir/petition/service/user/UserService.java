package com.bsuir.petition.service.user;

import com.bsuir.petition.bean.dto.user.*;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.exception.*;

public interface UserService {
    UserListDTO getUsers() throws ServerException;
    void updateUser(long id, UpdateUserDTO updateUserDTO) throws UserNotFoundException, ServerException, ErrorInputException;
    User getUser(String userEmail) throws UserNotFoundException, ErrorInputException;
    UserDTO getUser(long id) throws UserNotFoundException, ServerException;
    UserInformationDTO getUserInformation(long id) throws UserInformationNotFoundException, ServerException;
    User registration(UserRegistrationDTO userRegistrationDTO)
            throws SuchUserExistsException, ErrorInputException,
            DifferentPasswordsException, ServerException;

    void updateUserInformation(long id, UserInformationDTO userInformationDTO)
            throws ErrorInputException, ServerException;
}
