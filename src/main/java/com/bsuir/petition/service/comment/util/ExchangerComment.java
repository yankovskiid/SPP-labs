package com.bsuir.petition.service.comment.util;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.entity.Comment;

public interface ExchangerComment {
    Comment getComment(CommentDTO commentDTO, long id);
}
