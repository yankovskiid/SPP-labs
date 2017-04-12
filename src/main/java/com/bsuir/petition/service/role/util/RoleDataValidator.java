package com.bsuir.petition.service.role.util;

import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface RoleDataValidator {
    void validate(ShortRoleDTO shortRoleDTO) throws ErrorInputException;
}
