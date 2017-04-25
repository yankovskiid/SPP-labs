package com.bsuir.petition.service.comment.util;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface CommentDataValidator {
    void validate(ShortCommentDTO shortCommentDTO) throws ErrorInputException;
}
