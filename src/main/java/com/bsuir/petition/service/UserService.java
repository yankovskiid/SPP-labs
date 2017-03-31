package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

public interface UserService {
    User getUser(long id);
    User getUser(String userEmail);
    UserInformation getUserInformation(long id);
    void registration(UserRegistrationDTO userRegistrationDTO);
}
