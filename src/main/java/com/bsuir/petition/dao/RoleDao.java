package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);
    Role getRoleById(long id);
    List<Role> getRoles();

    Role createRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Role role);
}
