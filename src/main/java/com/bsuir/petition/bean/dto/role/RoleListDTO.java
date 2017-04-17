package com.bsuir.petition.bean.dto.role;

import com.bsuir.petition.bean.entity.Role;

import java.util.ArrayList;

public class RoleListDTO {

    private ArrayList<ShortRoleDTO> roles = new ArrayList<>();

    public ArrayList<ShortRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<ShortRoleDTO> roles) {
        this.roles = roles;
    }
}
