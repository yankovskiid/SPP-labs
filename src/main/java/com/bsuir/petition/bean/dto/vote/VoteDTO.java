package com.bsuir.petition.bean.dto.vote;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;

public class VoteDTO {
    private String reason;
    private long userId;
    private String name;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
