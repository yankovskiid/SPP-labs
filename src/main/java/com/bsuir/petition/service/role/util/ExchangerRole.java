package com.bsuir.petition.service.role.util;

import com.bsuir.petition.bean.dto.role.ShortRoleDTO;
import com.bsuir.petition.bean.entity.Role;

public interface ExchangerRole {
    Role getRole(ShortRoleDTO shortRoleDTO, long id);
    Role getRole(ShortRoleDTO shortRoleDTO);
}
