package com.bsuir.petition.controller;

import com.bsuir.petition.bean.dto.response.message.ErrorMessageDTO;
import com.bsuir.petition.service.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller")
public class ExceptionController {

    @ExceptionHandler(DifferentPasswordsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDTO differentPasswordExceptionHandler(DifferentPasswordsException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessageDTO wrongPasswordExceptionHandler(WrongPasswordException exception) {
        return getErrorMessage(exception);

    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessageDTO userNotFoundExceptionHandler(UserNotFoundException exception) {
        return getErrorMessage(exception);

    }

    @ExceptionHandler(ErrorInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDTO errorInputExceptionHandler(ErrorInputException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchUserExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDTO suchUserExistsExceptionHandler(SuchUserExistsException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(UserInformationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageDTO userInformationNotFoundExceptionHandler(UserInformationNotFoundException exception) {
        return getErrorMessage(exception);
    }

    private ErrorMessageDTO getErrorMessage(Exception exception) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }

}
