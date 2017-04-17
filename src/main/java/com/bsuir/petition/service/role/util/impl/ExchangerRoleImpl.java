package com.bsuir.petition.service.role.util.impl;

import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.dao.RoleDao;
import com.bsuir.petition.service.role.util.ExchangerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangerRoleImpl implements ExchangerRole {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) { this.roleDao = roleDao; }

    @Override
    public Role getRole(ShortRoleDTO shortRoleDTO, long id) {
        Role role = roleDao.getRoleById(id);
        role.setRoleName(shortRoleDTO.getName());

        return role;
    }

    @Override
    public Role getRole(ShortRoleDTO shortRoleDTO) {
        Role role = new Role();
        role.setRoleName(shortRoleDTO.getName());

        return role;
    }
}
