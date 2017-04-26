package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;

import java.util.Date;

public class PetitionDTO extends ShortPetitionDTO {
    private Date expiryDate;
    private UserInformationDTO userInformationDTO;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UserInformationDTO getUserInformationDTO() {
        return userInformationDTO;
    }

    public void setUserInformationDTO(UserInformationDTO userInformationDTO) {
        this.userInformationDTO = userInformationDTO;
    }
}
