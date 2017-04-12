package com.bsuir.petition.controller;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller")
public class ExceptionController {

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessageDTO differentPasswordExceptionHandler(ServerException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(ErrorInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO errorInputExceptionHandler(ErrorInputException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }

}
