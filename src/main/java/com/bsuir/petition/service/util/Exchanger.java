package com.bsuir.petition.service.util;

import com.bsuir.petition.bean.dto.user.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;

public interface Exchanger {
    User getUser(UserRegistrationDTO userRegistrationDTO);
}
