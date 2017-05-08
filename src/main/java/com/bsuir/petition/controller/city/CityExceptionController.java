package com.bsuir.petition.controller.city;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.exception.SuchCityExistsException;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.city")
public class CityExceptionController {
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO categoryNotFoundExceptionHandler(CityNotFoundException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO countryNotFoundExceptionHandler(CountryNotFoundException exception) {
        return getErrorMessage(exception);
    }


    @ExceptionHandler(SuchCityExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessageDTO suchCategoryExistsExceptionHandler(SuchCityExistsException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }
}
