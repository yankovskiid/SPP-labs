package com.bsuir.petition.service.util.impl;

import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.service.util.Exchanger;
import org.springframework.stereotype.Component;

@Component
public class ExchangerImpl implements Exchanger {
    @Override
    public User getUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setNick(userRegistrationDTO.getNick());
        return user;
    }
}
