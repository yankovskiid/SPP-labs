package com.bsuir.petition.controller.role.impl;

import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.controller.role.RoleController;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.role.RoleService;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.exception.SuchRoleExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleControllerImpl implements RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void addRole(@RequestBody ShortRoleDTO shortRoleDTO) throws ServerException, ErrorInputException, SuchRoleExistsException {
        roleService.addRole(shortRoleDTO);
    }

    @Override
    public void updateRole(@RequestBody ShortRoleDTO shortRoleDTO, @PathVariable long id) throws ErrorInputException, ServerException, RoleNotFoundException {
        roleService.updateRole(shortRoleDTO, id);
    }

    @Override
    public void deleteRole(@PathVariable long id) throws ServerException, RoleNotFoundException {
        roleService.deleteRole(id);
    }

    @Override
    public RoleListDTO getRoles() throws ServerException {
        return roleService.getRoles();
    }
}
