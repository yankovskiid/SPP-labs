package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Role;

public interface RoleDao {
    Role getRoleByName(String name);
    Role getRoleById(long id);
}
