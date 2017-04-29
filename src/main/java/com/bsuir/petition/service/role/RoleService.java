package com.bsuir.petition.service.role;

import com.bsuir.petition.bean.dto.role.RoleDTO;
import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.exception.SuchRoleExistsException;

public interface RoleService  {
    RoleListDTO getRoles() throws ServerException;
    void deleteRole(long id) throws RoleNotFoundException, ServerException;
    void updateRole(RoleDTO roleDTO, long id) throws RoleNotFoundException, ErrorInputException, ServerException;
    void addRole(RoleDTO roleDTO) throws ErrorInputException, ServerException, SuchRoleExistsException;
}
