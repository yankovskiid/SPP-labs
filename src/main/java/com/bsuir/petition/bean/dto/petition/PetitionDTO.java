package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.user.UserDTO;

import java.util.Date;

public class PetitionDTO extends ShortPetitionDTO {
    private Date expiryDate;
    private UserDTO user;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
