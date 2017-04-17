package com.bsuir.petition.service.role.util.impl;

import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.role.util.RoleDataValidator;
import org.springframework.stereotype.Component;

@Component
public class RoleDataValidatorImpl implements RoleDataValidator {

    @Override
    public void validate(ShortRoleDTO shortRoleDTO) throws ErrorInputException {
        validateString(shortRoleDTO.getName());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input");
        }
    }
}
