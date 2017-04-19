package com.bsuir.petition.bean.dto.vote;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.entity.Petition;

public class VoteDTO {
    private long id;
    private UserDTO userDTO;
    private long petitionId;
    private String reason;


    public long getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(long petitionId) {
        this.petitionId = petitionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
