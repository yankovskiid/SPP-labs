package com.bsuir.petition.controller;

import com.bsuir.petition.bean.dto.request.UserLoginDTO;
import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.dto.response.message.MessageDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.security.GetTokenService;
import com.bsuir.petition.service.DtoService;
import com.bsuir.petition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private GetTokenService getTokenService;

    private UserService userService;

    private DtoService dtoService;

    @Autowired
    public void setGetTokenService(GetTokenService getTokenService) {
        this.getTokenService = getTokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDtoService(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserInformationDTO getUser(@PathVariable long id) {
     /*   UserInformationDTO userInformationDTO;
        User user = userService.getUser(id);
        userInformationDTO = dtoService.getUserInformationDTO(user);
        return userInformationDTO;*/
     UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setSurname("dsa");
        userInformationDTO.setUsername("dsa");
        userInformationDTO.setCity("dsa");
        return userInformationDTO;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public MessageDTO getLogin(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        MessageDTO messageDTO = new MessageDTO();
        return messageDTO;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MessageDTO getSignUp(@RequestBody UserLoginDTO userLoginDTO) {
        MessageDTO messageDTO = new MessageDTO();
        String token = null;
        try {
            token = getTokenService.getToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (token == null) {
            messageDTO.setError(true);
            messageDTO.setMessage("Error authentication");
        } else {
            messageDTO.setError(false);
            messageDTO.setMessage(token);
        }
        return messageDTO;
    }
}