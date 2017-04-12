package com.bsuir.petition.service.user.util;

import com.bsuir.petition.bean.dto.user.UpdateUserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface UserDataValidator {
    void validate(UserRegistrationDTO userRegistrationDTO) throws ErrorInputException;
    void validate(UpdateUserDTO updateUserDTO) throws ErrorInputException;
    void validate(UserInformationDTO userInformationDTO) throws ErrorInputException;
    void validate(String email) throws ErrorInputException;
}
