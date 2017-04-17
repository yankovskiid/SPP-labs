package com.bsuir.petition.service.role.util.impl;

import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.service.role.util.DtoExchangerRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoExchangerRoleImpl implements DtoExchangerRole{
    @Override
    public RoleListDTO getRoleListDTO(List<Role> roles) {
        RoleListDTO roleListDTO = new RoleListDTO();
        ArrayList<ShortRoleDTO> shortRoles = roleListDTO.getRoles();
        for (Role role : roles) {
            ShortRoleDTO shortRoleDTO = new ShortRoleDTO();
            shortRoleDTO.setName(role.getRoleName());
            shortRoles.add(shortRoleDTO);
        }

        return roleListDTO;
    }
}
