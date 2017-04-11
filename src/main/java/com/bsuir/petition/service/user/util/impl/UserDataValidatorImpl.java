package com.bsuir.petition.service.user.util.impl;

import com.bsuir.petition.bean.dto.user.UpdateUserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.user.util.UserDataValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDataValidatorImpl implements UserDataValidator {

    @Override
    public void validate(String email) throws ErrorInputException {
        validateString(email);
        // TODO: 4/11/17 Add validate email
    }

    @Override
    public void validate(UpdateUserDTO updateUserDTO) throws ErrorInputException {
        ArrayList<String> roles = updateUserDTO.getRoles();
        if (roles == null || roles.size() == 0) {
            throw new ErrorInputException("No roles setted!");
        }

        for (String role : roles) {
            validateString(role);
        }
    }

    @Override
    public void validate(UserRegistrationDTO userRegistrationDTO) throws ErrorInputException {
        validateString(userRegistrationDTO.getNick());
        validateString(userRegistrationDTO.getRepeatPassword());
        validateString(userRegistrationDTO.getPassword());
        validate(userRegistrationDTO.getEmail());
    }

    @Override
    public void validate(UserInformationDTO userInformationDTO) throws ErrorInputException {
        validateString(userInformationDTO.getCity());
        validateString(userInformationDTO.getUsername());
        validateString(userInformationDTO.getSurname());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
