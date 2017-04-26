package com.bsuir.petition.controller.vote;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.vote.exception.SuchVoteExistsException;
import com.bsuir.petition.service.vote.exception.VoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.vote")
public class VoteExceptionController {
    @ExceptionHandler(VoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO voteNotFoundExceptionHandler(VoteNotFoundException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchVoteExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO suchVoteExistsExceptionHandler(SuchVoteExistsException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }
}
