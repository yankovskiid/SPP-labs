package com.bsuir.petition.bean.dto.comment;

import com.bsuir.petition.bean.dto.user.UserInformationDTO;

public class CommentDTO extends ShortCommentDTO {
    private String userNick;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
