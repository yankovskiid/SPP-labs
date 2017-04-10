package com.bsuir.petition.controller;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.security.service.exception.AuthenticationException;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.exception.SuchCategoryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.user.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller")
public class ExceptionController {

    /* ---USER EXCEPTIONS--- */


    @ExceptionHandler(DifferentPasswordsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO differentPasswordExceptionHandler(DifferentPasswordsException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessageDTO differentPasswordExceptionHandler(ServerException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public MessageDTO wrongPasswordExceptionHandler(WrongPasswordException exception) {
        return getErrorMessage(exception);

    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO userNotFoundExceptionHandler(UserNotFoundException exception) {
        return getErrorMessage(exception);

    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public MessageDTO authenticationExceptionHandler(AuthenticationException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(ErrorInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO errorInputExceptionHandler(ErrorInputException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchUserExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO suchUserExistsExceptionHandler(SuchUserExistsException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(UserInformationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO userInformationNotFoundExceptionHandler(UserInformationNotFoundException exception) {
        return getErrorMessage(exception);
    }


    /* ---CATEGORY EXCEPTIONS--- */


    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageDTO categoryNotFoundExceptionHandler(CategoryNotFoundException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchCategoryExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO suchCategoryExistsExceptionHandler(SuchCategoryExistsException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }

}
