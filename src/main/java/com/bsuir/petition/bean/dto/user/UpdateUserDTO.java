package com.bsuir.petition.bean.dto.user;

import java.util.ArrayList;

public class UpdateUserDTO {
    private boolean isBlocked;
    private ArrayList<String> roles;

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}
