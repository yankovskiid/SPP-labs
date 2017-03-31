package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;

public interface UserService {
    User getUser(long id);
    User getUser(String userEmail);
    void registration(UserRegistrationDTO userRegistrationDTO);
}
