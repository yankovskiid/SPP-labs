package com.bsuir.petition.service.comment.util.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.service.comment.util.CommentDataValidator;
import com.bsuir.petition.service.exception.ErrorInputException;
import org.springframework.stereotype.Component;

@Component
public class CommentDataValidatorImpl implements CommentDataValidator {
    @Override
    public void validate(ShortCommentDTO shortCommentDTO) throws ErrorInputException {
        validateString(shortCommentDTO.getText());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
