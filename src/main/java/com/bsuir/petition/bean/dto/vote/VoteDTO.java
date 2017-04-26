package com.bsuir.petition.bean.dto.vote;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;

public class VoteDTO {
//    private long id;
//    private UserDTO userDTO;
//    private long petitionId;
    private long userId;
    private String reason;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

//    public UserDTO getUserDTO() {
//        return userDTO;
//    }
//
//    public void setUserDTO(UserDTO userDTO) {
//        this.userDTO = userDTO;
//    }
}
