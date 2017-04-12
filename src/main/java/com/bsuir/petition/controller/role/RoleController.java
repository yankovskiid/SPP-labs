package com.bsuir.petition.controller.role;

import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.exception.SuchRoleExistsException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RoleController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    void addRole(ShortRoleDTO shortRoleDTO) throws ServerException, ErrorInputException, SuchRoleExistsException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/role/{id}")
    void updateRole(ShortRoleDTO shortRoleDTO, long id) throws ErrorInputException, ServerException, RoleNotFoundException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    void deleteRole(long id) throws ServerException, RoleNotFoundException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    RoleListDTO getRoles() throws ServerException;
}
