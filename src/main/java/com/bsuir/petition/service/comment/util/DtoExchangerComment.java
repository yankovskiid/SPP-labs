package com.bsuir.petition.service.comment.util;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.entity.Comment;

import java.util.List;

public interface DtoExchangerComment {
    CommentDTO getCommentDTO(Comment comment);
    CommentListDTO getCommentListDTO(List<Comment> comments);
}
