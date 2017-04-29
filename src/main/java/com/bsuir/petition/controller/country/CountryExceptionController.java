package com.bsuir.petition.controller.country;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.exception.SuchCountryExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.country")
public class CountryExceptionController {

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO categoryNotFoundExceptionHandler(CountryNotFoundException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchCountryExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO suchCountryExistsExceptionHandler(SuchCountryExistsException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }
}
