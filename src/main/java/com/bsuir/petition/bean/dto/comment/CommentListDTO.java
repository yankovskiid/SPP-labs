package com.bsuir.petition.bean.dto.comment;

import java.util.ArrayList;

public class CommentListDTO {
    private ArrayList<CommentDTO> comments = new ArrayList<>(0);

    public ArrayList<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentDTO> comments) {
        this.comments = comments;
    }
}
