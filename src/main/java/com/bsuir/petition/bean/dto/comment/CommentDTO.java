package com.bsuir.petition.bean.dto.comment;

import com.bsuir.petition.bean.dto.user.UserInformationDTO;

public class CommentDTO extends ShortCommentDTO {
    private UserInformationDTO userInfo;

    public UserInformationDTO getUser() {
        return userInfo;
    }

    public void setUser(UserInformationDTO userInfo) {
        this.userInfo = userInfo;
    }
}
