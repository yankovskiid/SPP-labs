package com.bsuir.petition.service.comment.util;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.bean.entity.Comment;

public interface ExchangerComment {
    Comment getComment(ShortCommentDTO shortCommentDTO, long id);
}
