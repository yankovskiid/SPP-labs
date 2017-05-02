package com.bsuir.petition.controller.petition;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.petition")
public class PetitionExceptionController {

    @ExceptionHandler(PetitionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO petitionNotFoundExceptionHandler(PetitionNotFoundException exception) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(exception.getMessage());
        return messageDTO;
    }

}
