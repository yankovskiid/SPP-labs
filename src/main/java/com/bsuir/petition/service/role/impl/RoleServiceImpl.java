package com.bsuir.petition.service.role.impl;

import com.bsuir.petition.bean.dto.role.RoleDTO;
import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.dao.RoleDao;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.role.RoleService;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.exception.SuchRoleExistsException;
import com.bsuir.petition.service.role.util.DtoExchangerRole;
import com.bsuir.petition.service.role.util.ExchangerRole;
import com.bsuir.petition.service.role.util.RoleDataValidator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private RoleDataValidator roleDataValidator;

    private RoleDao roleDao;

    private DtoExchangerRole dtoExchangerRole;

    private ExchangerRole exchangerRole;

    @Autowired
    public void setRoleDataValidator(RoleDataValidator roleDataValidator) {
        this.roleDataValidator = roleDataValidator;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setDtoExchangerRole(DtoExchangerRole dtoExchangerRole) {
        this.dtoExchangerRole = dtoExchangerRole;
    }

    @Autowired
    public void setExchangerRole(ExchangerRole exchangerRole) {
        this.exchangerRole = exchangerRole;
    }

    @Override
    @Transactional
    public RoleListDTO getRoles() throws ServerException {
        List<Role> roles;
        roles = roleDao.getRoles();

        return dtoExchangerRole.getRoleListDTO(roles);
    }

    @Override
    public void deleteRole(long id) throws RoleNotFoundException, ServerException {
       try {
           Role role = roleDao.getRoleById(id);
           if (role != null) {
               roleDao.deleteRole(role);
           } else {
               throw new RoleNotFoundException("Role not found");
           }
       } catch (HibernateException e) {
           throw new ServerException("Server exception", e);
       }
    }

    @Override
    public void updateRole(RoleDTO roleDTO, long id) throws RoleNotFoundException, ErrorInputException, ServerException {
        try {
            Role role = roleDao.getRoleById(id);
            if (role != null) {
                role.setRoleName(roleDTO.getName());
                roleDao.updateRole(role);
            } else {
                throw new RoleNotFoundException("Role not found");
            }
        } catch (HibernateException e) {
            throw new ServerException("Server exception", e);
        }

    }

    @Override
    public void addRole(RoleDTO roleDTO) throws ErrorInputException, ServerException, SuchRoleExistsException {
        Role role = new Role();
        try {
            role.setRoleName(roleDTO.getName());
            roleDao.createRole(role);
        } catch (HibernateException e) {
            throw new SuchRoleExistsException("Such role exists!");
        }
    }
}
