package com.bsuir.petition.service.role.util;

import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.entity.Role;

import java.util.List;

public interface DtoExchangerRole {
    RoleListDTO getRoleListDTO(List<Role> roles);
}
