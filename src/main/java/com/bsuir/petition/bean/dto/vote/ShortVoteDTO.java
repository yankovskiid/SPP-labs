package com.bsuir.petition.bean.dto.vote;

import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;

public class ShortVoteDTO {
    private String reason;
//    private Petition petition;
//    private User user;
    private long petitionId;
    private long userId;

//    public User getUserInformationDTO() {
//        return user;
//    }

//    public void setUserInformationDTO(User user) {
//        this.user = user;
//    }

//    public Petition getPetition() {
//        return petition;
//    }

//    public void setPetition(Petition petition) {
//        this.petition = petition;
//    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(long petitionId) {
        this.petitionId = petitionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
