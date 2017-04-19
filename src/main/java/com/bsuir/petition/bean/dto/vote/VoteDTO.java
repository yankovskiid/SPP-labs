package com.bsuir.petition.bean.dto.vote;

import com.bsuir.petition.bean.entity.Petition;

public class VoteDTO {
    private long id;
    private long userId;
    private long petitionId;
    private Petition petition;
    private String reason;

    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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
}
