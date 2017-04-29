package com.bsuir.petition.controller.role;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.exception.SuchRoleExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice (basePackages = "com.bsuir.petition.controller.role")
public class RoleExceptionController {
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO roleNotFoundExceptionHandler(RoleNotFoundException e) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(e.getMessage());

        return messageDTO;
    }

    @ExceptionHandler(SuchRoleExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO suchRoleExistsException(SuchRoleExistsException e) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(e.getMessage());

        return messageDTO;
    }
}
