package com.bsuir.petition.bean.dto.comment;

import com.bsuir.petition.bean.dto.user.UserDTO;

public class CommentDTO {
    private String text;
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}
