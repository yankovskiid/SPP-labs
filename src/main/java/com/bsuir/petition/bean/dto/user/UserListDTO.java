package com.bsuir.petition.bean.dto.user;

import java.util.ArrayList;

public class UserListDTO {
    private ArrayList<ShortUserInformationDTO> users = new ArrayList<>(0);

    public ArrayList<ShortUserInformationDTO> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<ShortUserInformationDTO> users) {
        this.users = users;
    }
}
