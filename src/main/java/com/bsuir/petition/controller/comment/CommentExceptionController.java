package com.bsuir.petition.controller.comment;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.comment")
public class CommentExceptionController {

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageDTO petitionNotFoundExceptionHandler(CommentNotFoundException exception) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(exception.getMessage());
        return messageDTO;
    }

}
